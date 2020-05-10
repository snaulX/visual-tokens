package com.snaulX.VisualTokens.operations

import com.snaulX.VisualTokens.app.Operator

class PlusOperator: Operator {
    override var firstValue: String = ""
    override var secondValue: String = ""

    override fun eval(): String {
        return firstValue + secondValue
    }
}