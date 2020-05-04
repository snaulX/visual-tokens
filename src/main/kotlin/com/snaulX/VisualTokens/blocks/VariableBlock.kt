package com.snaulX.VisualTokens.blocks

import com.snaulX.VisualTokens.app.Block
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import tornadofx.*

class VariableBlock : Block {
    private var nameBox: TextField by singleAssign()
    var name: String
        get() = nameBox.text
        set(value) {
            nameBox.text = value
        }
    override val root: HBox = HBox()
    override var select: Boolean = false

    override fun run(blocks: List<Block>) {
        with(root) {
            paddingAll = 10.0
            style = "-fx-background-color: blue;"
            label("Create Variable") {
                paddingRight = 10.0
            }
            nameBox = textfield("Write name of variable")
        }
    }


}