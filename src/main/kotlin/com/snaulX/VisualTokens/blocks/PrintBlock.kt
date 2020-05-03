package com.snaulX.VisualTokens.blocks

import com.snaulX.VisualTokens.app.Block
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import tornadofx.*

class PrintBlock : Block {
    override val root = HBox()
    val value: String
        get() = text.text
    private var text: TextField by singleAssign()
    override var select: Boolean = false

    init {
        with(root) {
            paddingAll = 10.0
            style = "-fx-background-color: orange;"
            label("Print To Console") {
                paddingRight = 10.0
            }
            text = textfield("Type there are text for prinitng")
            setOnMouseClicked {
                select = !select
            }
        }
    }
}