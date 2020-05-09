package com.snaulX.VisualTokens.blocks.operations

import com.snaulX.VisualTokens.app.Block
import javafx.geometry.Insets
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import tornadofx.*

class PlusNumberBlock: Block {
    override val root: HBox = HBox()
    override var select: Boolean = false
    private var firstValue: TextField by singleAssign()

    init {
        with(root) {
            paddingAll = 10.0
            firstValue = textfield("Insert first value")
            label("+") {
                hboxConstraints {
                    margin = Insets(10.0)
                }
            }
        }
    }

    override fun run(blocks: List<Block>) {
        TODO("Not yet implemented")
    }
}