package com.snaulX.VisualTokens.blocks

import com.snaulX.VisualTokens.app.Block
import com.snaulX.VisualTokens.app.Parser
import com.snaulX.VisualTokens.app.Parser.setBackground
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import tornadofx.*

class VariableBlock : Block {
    private var nameBox: TextField by singleAssign()
    private var valueBox: TextField by singleAssign()
    var name: String
        get() = nameBox.text.trim()
        set(value) {
            nameBox.text = value
        }
    var value: String
        get() = valueBox.text
        set(value) {
            valueBox.text = value
        }
    override val root: HBox = HBox()
    override var select: Boolean = false

    init {
        with(root) {
            paddingAll = 10.0
            setBackground("blue")
            label("Create Variable") {
                paddingRight = 10.0
            }
            nameBox = textfield("Write name of variable") {
                hboxConstraints {
                    marginRight = 10.0
                }
            }
            valueBox = textfield("Write value of variable")
        }
        onSelect()
    }

    override fun run(blocks: List<Block>) {
        Parser.variables.put(name, value)
    }
}