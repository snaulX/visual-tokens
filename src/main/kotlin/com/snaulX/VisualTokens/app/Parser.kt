package com.snaulX.VisualTokens.app

import com.snaulX.VisualTokens.blocks.EndBlock
import com.snaulX.VisualTokens.blocks.IfBlock
import com.snaulX.VisualTokens.blocks.PrintBlock
import com.snaulX.VisualTokens.blocks.VariableBlock
import com.snaulX.VisualTokens.operations.*
import com.snaulX.VisualTokens.views.Worksheet
import com.snaulX.VisualTokens.views.operators
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import javafx.scene.layout.Pane
import tornadofx.*
import java.io.File
import java.lang.Thread.sleep

object Parser {
    val varCode: Regex = Regex("""vtvar_(.+)\?""")
    val opCode: Regex = Regex("""vtop_(\S+|\D+)!(.+)!(.+)!""")
    val variables: MutableMap<String, String> = mutableMapOf()
    val levelOfBlocks: MutableList<Boolean> = mutableListOf()
    private var input = ""

    private fun input(): String {
        find(Worksheet::class).dialog("Input") {
            val inputField = textfield()
            button("OK").action {
                input = inputField.text
                this.close()
            }
        }
        return input
    }

    fun run(work: Worksheet) {
        levelOfBlocks.add(true)
        for (block in work.blocks) {
            if (levelOfBlocks.last() || block is EndBlock) {
                block.run(work.blocks)
            }
        }
        levelOfBlocks.clear()
    }

    fun parseString(str: String): String {
        val s = (opCode.replace(varCode.replace(str) {
            return@replace variables[it.destructured.component1()]!!
        }) {
            val res: MatchResult.Destructured = it.destructured
            val operator: Operator = when (res.component1()) {
                operators[0] -> PlusOperator()
                operators[1] -> EqualsOperator()
                operators[2] -> NotOperator()
                operators[3] -> OrOperator()
                operators[4] -> AndOperator()
                operators[5] -> GtOperator()
                operators[6] -> LtOperator()
                operators[7] -> MinusOperator()
                operators[8] -> PlusNumberOperator()
                operators[9] -> MultiplyOperator()
                operators[10] -> DivideOperator()
                operators[11] -> ModuloOperator()
                else -> throw Exception("Invalid operator code")
            }
            operator.firstValue = res.component2()
            operator.secondValue = res.component3()
            return@replace operator.eval()
        })
        if (s.contains("%input%")) {
            var input = input()
            while (input.isEmpty()) sleep(0)
            s.replace("%input%", input)
        }
        return s
    }

    fun compile(worksheet: Worksheet) {
        val file = FileSpec.builder("",
                worksheet.title.removeSuffix(" - Visual Tokens"))
        val funBuilder = FunSpec.builder("main")
        for (block in worksheet.blocks) {
            when (block) {
                is PrintBlock -> funBuilder.addStatement("println(%P)",
                        varCode.replace(block.value) {
                            return@replace "\$${it.destructured.component1()}"
                        })
                is VariableBlock -> funBuilder.addStatement(
                        "var ${block.name} = \"\"\"${block.value}\"\"\"")
                is IfBlock -> funBuilder.addStatement("if (${block.statementValue}) {")
                is EndBlock -> funBuilder.addStatement("}")
            }
        }
        file.addFunction(funBuilder.build())
                .build()
                .writeTo(File("${file.name} Tokens Project"))
    }

    fun ByteArray.toBlocks(): List<Block> {
        val blocks: MutableList<Block> = mutableListOf()
        var i = 0
        while (i < this.size) {
            blocks.add(when (this[i].toInt()) {
                0 -> PrintBlock()
                1 -> VariableBlock()
                else -> throw Exception("Invalid code of block ${this[i]}")
            }.apply {
                i = read(i, this@toBlocks)
            })
        }
        return blocks.toList()
    }

    fun Pane.setBackground(bg: String) {
        this.style += "-fx-background-color: $bg;"
    }
}