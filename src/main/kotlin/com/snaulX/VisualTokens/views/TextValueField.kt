package com.snaulX.VisualTokens.views

import javafx.scene.control.TextField
import tornadofx.*

class TextValueField(text: String?) : TextField(text) {
    init {
        contextmenu {
            //pass
        }
    }
}