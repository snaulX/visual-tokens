package com.snaulX.VisualTokens.app

interface Operator {
    var firstValue: String
    var secondValue: String

    fun eval(): String
}