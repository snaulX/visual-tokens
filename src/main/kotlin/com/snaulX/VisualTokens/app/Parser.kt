package com.snaulX.VisualTokens.app

import com.snaulX.VisualTokens.views.Worksheet
import java.lang.StringBuilder

object Parser {
    const val varCode = "$$\$var_"
    val stringVariables: MutableMap<String, String> = mutableMapOf()
    val intVariables: MutableMap<String, Int> = mutableMapOf()
    val doubleVariables: MutableMap<String, Double> = mutableMapOf()
    val boolVariables: MutableMap<String, Boolean> = mutableMapOf()

    fun run(work: Worksheet) {
        for (block in work.blocks) {
            block.run(work.blocks)
        }
    }

    fun parseString(str: String): String {
        var ind = str.indexOf(varCode)
        while (ind != -1) {
            val buffer: StringBuilder = StringBuilder()
            str.substring(ind + varCode.length).forEach { c: Char ->
                if (c == '?') return@forEach
                buffer.append(c)
            }
            val varName = buffer.toString()
            str.replaceRange(ind, ind + varCode.length + varName.length, (
                    try {
                        stringVariables[varName]!!
                    } catch (e: NullPointerException) {
                        try {
                            intVariables[varName]!!
                        } catch (e: NullPointerException) {
                            try {
                                doubleVariables[varName]!!
                            } catch (e: NullPointerException) {
                                try {
                                    boolVariables[varName]!!
                                } catch (e: NullPointerException) {
                                    "{Unknown variable with name $varName}"
                                }
                            }
                        }
                    }
                    ).toString())
            ind = str.indexOf(varCode)
        }
        return str
    }
}