package com.snaulX.VisualTokens.app

import com.snaulX.VisualTokens.views.Worksheet
import javafx.scene.Node
import javafx.scene.layout.Pane

object Parser {
    val varCode: Regex = Regex("""vtvar_(\S+)\?""")
    val variables: MutableMap<String, String> = mutableMapOf()

    fun run(work: Worksheet) {
        for (block in work.blocks) {
            block.run(work.blocks)
        }
    }

    fun parseString(str: String): String {
        return varCode.replace(str) {
            return@replace variables[it.destructured.component1()]!!
        }
    }

    fun Node.setBackground(bg: String) {
        this.style += "-fx-background-color: $bg;"
    }

    fun Pane.setBackground(bg: String) {
        this.style += "-fx-background-color: $bg;"
    }
}