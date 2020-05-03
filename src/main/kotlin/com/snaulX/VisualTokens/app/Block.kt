package com.snaulX.VisualTokens.app

import javafx.scene.layout.HBox

interface Block {
    val root: HBox
    var select: Boolean
}