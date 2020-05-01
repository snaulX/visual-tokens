package com.snaulX.VisualTokens.views

import com.snaulX.VisualTokens.blocks.*
import tornadofx.*

class Worksheet() : Fragment("Visual Tokens Worksheet") {
    override val root = gridpane {
        row {
            StringBlock().root
        }
    }

    constructor(title: String) : this() {
        this.title = title
    }
}
