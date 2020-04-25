package com.snaulX.VisualTokens.views

import javafx.geometry.Pos
import javafx.scene.input.KeyCombination
import javafx.stage.Screen
import tornadofx.*

class MainView : View("Visual Tokens") {
    
    val newFile = {
        println("New File")
    }
    val openFile = {
        println("Open File")
    }
    val saveFile = {
        println("Save File")
    }

    override val root = anchorpane {
        gridpane {
            val rect = Screen.getPrimary().bounds
            maxHeight = rect.height
            maxWidth = rect.width
            row {
                menubar {
                    menu {
                        text = "File"
                        menubutton {
                             text = "New File"
                             shortcut(KeyCombination.keyCombination("ctrl+n"), newFile)
                        }
                        menubutton {
                            text = "Open File"
                            shortcut(KeyCombination.keyCombination("ctrl+o"), openFile)
                        }
                    }
                }
            }
            row {
                buttonbar {
                    button("Compile")
                    button("Run")
                    button("Compile & Run")
                }
            }
            row {
                addColumn(0,
                    hbox {
                    //blocks
                })
                /*listview<Block> {

                }*/
            }
            row {
                label {
                    alignment = Pos.CENTER
                    text = "Visual Tokens"
                }
            }
            row {
                label {
                    alignment = Pos.BOTTOM_CENTER
                    text = "Created by snaulX in 2020"
                }
            }
        }
    }
}
