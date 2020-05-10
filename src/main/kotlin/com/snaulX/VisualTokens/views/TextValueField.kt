package com.snaulX.VisualTokens.views

import javafx.scene.control.TextField
import tornadofx.*

class TextValueField() : TextField("") {
    constructor(text: String?) : this() {
        this.text = text
        setOnContextMenuRequested {
            contextmenu {
                item("Add Variable")
                item("Add Operator")
            }
        }
    }
}