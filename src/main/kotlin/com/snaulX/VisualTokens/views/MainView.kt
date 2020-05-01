package com.snaulX.VisualTokens.views

import com.snaulX.VisualTokens.blocks.StringBlock
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.input.KeyCombination
import javafx.stage.Screen
import tornadofx.*

class MainView : View("Visual Tokens") {
    
    val newFile = {
        Worksheet("Untitled").openWindow()
        println() //why lambda wanna return last expression and I must write this shit
    }
    val openFile = {
    }
    val saveFile = {
    }
    val exit = {
        this.close()
    }

    override val root = anchorpane {
        gridpane {
            val rect = Screen.getPrimary().bounds
            maxHeight = rect.height
            maxWidth = rect.width
            row {
                menubar {
                    menu("File") {
                        item("New File", "ctrl+n").action(newFile)
                        item("Open File", "ctrl+o").action(openFile)
                        item("Save File", "ctrl+s").action(saveFile)
                        separator()
                        item("Exit", "ctrl+q").action(exit)
                    }
                }
            }
            row {
                buttonbar {
                    paddingAll = 20.0
                    button("Compile")
                    button("Run")
                    button("Compile & Run")
                }
            }
            row {
                paddingAll = 20.0
                label {
                    alignment = Pos.CENTER
                    text = "Visual Tokens"
                }
            }
            row {
                paddingAll = 20.0
                label {
                    alignment = Pos.BOTTOM_CENTER
                    text = "Created by snaulX in 2020"
                }
            }
        }
    }
}
