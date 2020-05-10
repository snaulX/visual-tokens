package com.snaulX.VisualTokens.operations

import com.snaulX.VisualTokens.app.Operator

class GtOperator: Operator {
    override var firstValue: String = ""
    override var secondValue: String = ""

    override fun eval(): String {
        return (firstValue.toInt() > secondValue.toInt()).toString()
    }

}

class LtOperator: Operator {
    override var firstValue: String = ""
    override var secondValue: String = ""

    override fun eval(): String {
        return (firstValue.toInt() < secondValue.toInt()).toString()
    }

}

class OrOperator: Operator {
    override var firstValue: String = ""
    override var secondValue: String = ""

    override fun eval(): String {
        return (firstValue.toBoolean() || secondValue.toBoolean()).toString()
    }
}

class AndOperator: Operator {
    override var firstValue: String = ""
    override var secondValue: String = ""

    override fun eval(): String {
        return (firstValue.toBoolean() && secondValue.toBoolean()).toString()
    }
}

class EqualsOperator: Operator {
    override var firstValue: String = ""
    override var secondValue: String = ""

    override fun eval(): String {
        return (firstValue == secondValue).toString()
    }
}

class NotOperator: Operator {
    override var firstValue: String = ""
    override var secondValue: String = ""

    override fun eval(): String {
        return (!firstValue.toBoolean()).toString()
    }
}