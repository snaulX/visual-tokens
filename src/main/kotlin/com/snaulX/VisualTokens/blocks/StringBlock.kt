package com.snaulX.VisualTokens.blocks

import com.snaulX.VisualTokens.app.Block
import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import tornadofx.*

class StringBlock: Block {
    override val root = HBox()
    private var stringValue: TextField by singleAssign()

    init {
        with(root) {
            paddingAll = 10.0
            style = "-fx-background-color: orange;"
            label("String Value") {
                paddingRight = 10.0
            }
            stringValue = textfield {
                alignment = Pos.CENTER
            }
        }
    }

    val value: String = stringValue.text
}