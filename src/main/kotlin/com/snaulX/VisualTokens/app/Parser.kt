package com.snaulX.VisualTokens.app

import com.snaulX.VisualTokens.views.Worksheet

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
}