package com.snaulX.OwnLangBuilder.views

import javafx.geometry.Pos
import javafx.stage.Screen
import tornadofx.*

class MainView : View("Own Language Builder") {

    override val root = anchorpane {
        gridpane {
            val rect = Screen.getPrimary().bounds
            maxHeight = rect.height
            maxWidth = rect.width
            row {
                menubar {
                    menu {
                        text = "File"
                    }
                }
            }
            row {
                button {
                    var counter = 0
                    text = "Click me!"
                    action {
                        text = "Button was clicked ${++counter}"
                    }
                }
            }
            row {
                label {
                    alignment = Pos.CENTER
                    text = "Own Language Builder"
                }
            }
            row {
                label {
                    alignment = Pos.BOTTOM_CENTER
                    text = "Сделано Гунгером Александром 2020"
                }
            }
        }
    }
}
