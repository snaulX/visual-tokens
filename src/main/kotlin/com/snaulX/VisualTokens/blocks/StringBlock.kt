package com.snaulX.VisualTokens.blocks

import com.snaulX.VisualTokens.app.Block
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import tornadofx.*

class StringBlock: Block {
    override val root = HBox()
    private var stringValue: TextField by singleAssign()

    init {
        with(root) {
            label("String Value")
            stringValue = textfield()
        }
    }

    val value: String = stringValue.text
}