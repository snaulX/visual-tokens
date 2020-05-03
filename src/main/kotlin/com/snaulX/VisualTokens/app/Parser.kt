package com.snaulX.VisualTokens.app

import com.snaulX.VisualTokens.blocks.*
import com.snaulX.VisualTokens.views.Worksheet

object Parser {
    fun run(work: Worksheet) {
        for (block in work.blocks) {
            when (block) {
                is PrintBlock -> println(block.value)
            }
        }
    }
}