package com.snaulX.VisualTokens.blocks

import com.snaulX.VisualTokens.app.Block
import com.snaulX.VisualTokens.app.Parser
import com.snaulX.VisualTokens.app.Parser.setBackground
import com.snaulX.VisualTokens.views.textvaluefield
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import tornadofx.*

class PrintBlock() : Block {
    override val code: Byte = 0
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
        val ba = ByteArray(2 + value.length)
        ba[0] = code
        ba[1] = if (select) 1 else 0
        for (i in 2..ba.lastIndex) {
            ba[i] = value[i - 2].toByte()
        }
        return ba
    }

    override fun read(start: Int, data: ByteArray): Int {
        return start
    }

    init {
        with(root) {
            paddingAll = 10.0
            setBackground("orange")
            label("Print To Console") {
                paddingRight = 10.0
            }
            text = textvaluefield("Type there are text for prinitng")
        }
        onSelect()
    }

    constructor(value: String) : this() {
        this.value = value
    }
}