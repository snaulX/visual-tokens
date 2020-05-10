package com.snaulX.VisualTokens.app

interface Operator: Block {
    var firstValue: String
    var secondValue: String

    fun eval(): String
    override fun run(blocks: List<Block>) {
        eval()
    }
}