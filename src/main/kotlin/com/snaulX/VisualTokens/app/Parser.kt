package com.snaulX.VisualTokens.app

import com.snaulX.VisualTokens.blocks.PrintBlock
import com.snaulX.VisualTokens.blocks.VariableBlock
import com.snaulX.VisualTokens.operations.PlusOperator
import com.snaulX.VisualTokens.views.Worksheet
import com.snaulX.VisualTokens.views.operators
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import javafx.scene.Node
import javafx.scene.layout.Pane
import java.io.File

object Parser {
    val varCode: Regex = Regex("""vtvar_(\S+)\?""")
    val opCode: Regex = Regex("""vtop_(\S+|\D+)!(\S+)!(\S+)!""")
    val variables: MutableMap<String, String> = mutableMapOf()

    fun run(work: Worksheet) {
        for (block in work.blocks) {
            block.run(work.blocks)
        }
    }

    fun parseString(str: String): String {
        return opCode.replace(varCode.replace(str) {
            println(it.destructured.component1())
            return@replace variables[it.destructured.component1()]!!
        }) {
            val res: MatchResult.Destructured = it.destructured
            val operator: Operator = when (res.component1()) {
                operators[0] -> PlusOperator()
                else -> throw Exception("Invalid operator code")
            }
            operator.firstValue = res.component2()
            operator.secondValue = res.component3()
            return@replace operator.eval()
        }
    }

    fun compile(worksheet: Worksheet) {
        val file = FileSpec.builder("",
                worksheet.title.removeSuffix(" - Visual Tokens"))
        val funBuilder = FunSpec.builder("main")
                .addParameter("args", Array<String>::class)
        for (block in worksheet.blocks) {
            when (block) {
                is PrintBlock -> funBuilder.addStatement("println(%P)",
                        varCode.replace(block.value) {
                            return@replace "\$${it.destructured.component1()}"
                        })
                is VariableBlock -> funBuilder.addStatement(
                        "var ${block.name} = \"\"\"${block.value}\"\"\"")
            }
        }
        file.addFunction(funBuilder.build())
                .build()
                .writeTo(File(file.name + ".kt"))
    }

    fun Node.setBackground(bg: String) {
        this.style += "-fx-background-color: $bg;"
    }

    fun Pane.setBackground(bg: String) {
        this.style += "-fx-background-color: $bg;"
    }
}