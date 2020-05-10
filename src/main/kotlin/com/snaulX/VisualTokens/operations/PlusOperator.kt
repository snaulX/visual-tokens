package com.snaulX.VisualTokens.operations

import com.snaulX.VisualTokens.app.Operator
import javafx.geometry.Insets
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import com.snaulX.VisualTokens.app.Parser.setBackground
import tornadofx.*

class PlusOperator: Operator {
    override val code: Byte = 50
    override val root: HBox = HBox()
    override var select: Boolean = false
    private var first: TextField by singleAssign()
    private var second: TextField by singleAssign()
    override var firstValue: String
        get() = first.text
        set(value) {
            first.text = value
        }
    override var secondValue: String
        get() = second.text
        set(value) {
            second.text = value
        }

    init {
        with(root) {
            paddingAll = 10.0
            setBackground("lightblue")
            first = textfield("Insert first value")
            label("+") {
                paddingAll = 10.0
            }
            second = textfield("Insert second value")
        }
    }

    override fun eval(): String {
        return firstValue + secondValue
    }

    override fun toBytes(): ByteArray {
        val ba: ByteArray = ByteArray(2)
        ba[0] = code
        ba[1] = if (select) 1 else 0
        return ba
    }
}