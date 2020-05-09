package com.snaulX.VisualTokens.blocks

import com.snaulX.VisualTokens.app.Block
import com.snaulX.VisualTokens.app.Parser
import com.snaulX.VisualTokens.app.Parser.setBackground
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import tornadofx.*

class PrintBlock() : Block {
    override val root = HBox()
    var value: String
        get() = text.text
        set(v) { text.text = v }
    private var text: TextField by singleAssign()
    override var select: Boolean = false

    override fun run(blocks: List<Block>) {
        println(Parser.parseString(value))
    }

    override fun toBytes(): ByteArray {
        val ba = ByteArray(4 + value.length)
        ba[0] = 0 //
        ba[1] = 0 // Code of block
        ba[2] = 0 //
        ba[3] = if (select) 1 else 0
        for (i in ba[4]..ba.last()) {
            println(i)
        }
        return ba
    }

    init {
        with(root) {
            paddingAll = 10.0
            setBackground("orange")
            label("Print To Console") {
                paddingRight = 10.0
            }
            text = textfield("Type there are text for prinitng")
        }
        onSelect()
    }

    constructor(value: String) : this() {
        this.value = value
    }
}