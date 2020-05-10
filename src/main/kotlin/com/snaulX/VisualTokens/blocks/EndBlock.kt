package com.snaulX.VisualTokens.blocks

import com.snaulX.VisualTokens.app.Block
import com.snaulX.VisualTokens.app.Parser
import javafx.scene.layout.HBox
import com.snaulX.VisualTokens.app.Parser.setBackground
import tornadofx.*

class EndBlock: Block {
    override val code: Byte = 2
    override val root: HBox = HBox()
    override var select: Boolean = false

    init {
        with(root) {
            paddingAll = 10.0
            setBackground("darkgreen")
            label("End of block")
        }
    }

    override fun run(blocks: List<Block>) {
        Parser.levelOfBlocks.removeAt(Parser.levelOfBlocks.lastIndex)
    }

    override fun toBytes(): ByteArray {
        val ba: ByteArray = ByteArray(2)
        return ba
    }

    override fun read(start: Int, data: ByteArray): Int {
        return start
    }
}