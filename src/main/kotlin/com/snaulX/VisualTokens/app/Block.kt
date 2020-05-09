package com.snaulX.VisualTokens.app

import javafx.scene.control.ComboBox
import javafx.scene.layout.HBox
import tornadofx.*

interface Block {
    val root: HBox
    var select: Boolean

    /**
     * Implement [select] for all blocks
     */
    fun onSelect() {
        root.setOnMouseClicked {
            select = !select
        }
    }
    fun run(blocks: List<Block>)
}