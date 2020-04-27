package com.snaulX.VisualTokens.blocks

import com.snaulX.VisualTokens.app.Block
import javafx.scene.layout.HBox
import tornadofx.*

class PrintBlock() : Block {
    override val root = HBox()

    init {
        with(root) {
            label("Print to console")
        }
    }
}