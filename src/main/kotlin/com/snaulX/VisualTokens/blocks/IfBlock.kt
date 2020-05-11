package com.snaulX.VisualTokens.blocks

import com.snaulX.VisualTokens.app.Block
import com.snaulX.VisualTokens.app.Parser
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import tornadofx.*
import com.snaulX.VisualTokens.app.Parser.setBackground
import com.snaulX.VisualTokens.views.textvaluefield

class IfBlock: Block {
    override val code: Byte = 3
    override val root: HBox = HBox()
    override var select: Boolean = false
    var statement: TextField by singleAssign()
    var statementValue: String
        get() = statement.text
        set(value) {
            statement.text = value
        }

    init {
        with(root) {
            setBackground("green")
            label("If") {
                paddingAll = 10.0
            }
            statement = textvaluefield("Write statement using operators")
            label("then") {
                paddingAll = 10.0
            }
        }
    }

    override fun run(blocks: List<Block>) {
        Parser.levelOfBlocks.add(Parser.parseString(statementValue).toBoolean())
    }

    override fun toBytes(): ByteArray {
        val ba: ByteArray = ByteArray(2)
        return ba
    }

    override fun read(start: Int, data: ByteArray): Int {
        TODO("Not yet implemented")
    }
}