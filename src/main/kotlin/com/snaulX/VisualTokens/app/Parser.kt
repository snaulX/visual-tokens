package com.snaulX.VisualTokens.app

import com.snaulX.VisualTokens.operations.PlusOperator
import com.snaulX.VisualTokens.views.Worksheet
import javafx.scene.Node
import javafx.scene.layout.Pane

object Parser {
    val varCode: Regex = Regex("""vtvar_(\S+)\?""")
    val opCode: Regex = Regex("""vtop_(\S+|\D+)&(\S+)&(\S+)\?""")
    val variables: MutableMap<String, String> = mutableMapOf()

    fun run(work: Worksheet) {
        for (block in work.blocks) {
            block.run(work.blocks)
        }
    }

    fun parseString(str: String): String {
        return opCode.replace(varCode.replace(str) {
            return@replace variables[it.destructured.component1()]!!
        }) {
            val res: MatchResult.Destructured = it.destructured
            val operator: Operator = when (res.component1()) {
                "+" -> PlusOperator()
                else -> throw Exception("Invalid operator code")
            }
            operator.firstValue = res.component2()
            operator.secondValue = res.component3()
            return@replace operator.eval()
        }
    }

    fun Node.setBackground(bg: String) {
        this.style += "-fx-background-color: $bg;"
    }

    fun Pane.setBackground(bg: String) {
        this.style += "-fx-background-color: $bg;"
    }
}