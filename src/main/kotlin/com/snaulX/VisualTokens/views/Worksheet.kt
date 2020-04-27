package com.snaulX.VisualTokens.views

import com.snaulX.VisualTokens.blocks.*
import tornadofx.*

class Worksheet : Fragment("Visual Tokens Worksheet") {
    override val root = borderpane {
        StringBlock().root
    }
}
