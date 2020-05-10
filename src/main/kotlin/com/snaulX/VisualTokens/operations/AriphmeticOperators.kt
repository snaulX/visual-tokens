package com.snaulX.VisualTokens.operations

import com.snaulX.VisualTokens.app.Operator

class PlusNumberOperator: Operator {
    override var firstValue: String = ""
    override var secondValue: String = ""

    override fun eval(): String {
        return (firstValue.toInt() + secondValue.toInt()).toString()
    }
}

class MinusOperator: Operator {
    override var firstValue: String = ""
    override var secondValue: String = ""

    override fun eval(): String {
        return (firstValue.toInt() - secondValue.toInt()).toString()
    }
}

class MultiplyOperator: Operator {
    override var firstValue: String = ""
    override var secondValue: String = ""

    override fun eval(): String {
        return (firstValue.toInt() * secondValue.toInt()).toString()
    }
}

class DivideOperator: Operator {
    override var firstValue: String = ""
    override var secondValue: String = ""

    override fun eval(): String {
        return (firstValue.toInt() / secondValue.toInt()).toString()
    }
}

class ModuloOperator: Operator {
    override var firstValue: String = ""
    override var secondValue: String = ""

    override fun eval(): String {
        return (firstValue.toInt() % secondValue.toInt()).toString()
    }
}