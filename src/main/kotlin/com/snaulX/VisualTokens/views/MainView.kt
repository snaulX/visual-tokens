package com.snaulX.VisualTokens.views

import com.snaulX.VisualTokens.app.FileWorker
import javafx.geometry.Pos
import javafx.scene.text.Font
import javafx.stage.Screen
import tornadofx.*

class MainView : View("Visual Tokens") {
    
    val newFile: () -> Unit = {
        this.close()
        Worksheet("Untitled").openModal()
    }
    val openFile: () -> Unit = {
        FileWorker.open()?.openWindow()
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
                        separator()
                        item("Exit", "ctrl+q").action {
                            this@MainView.close()
                        }
                    }
                }
            }
            row {
                paddingAll = 100.0
                alignment = Pos.CENTER
                label {
                    text = """For create new program - Press Ctrl+N or File->New File
                        |For open your program - Press Ctrl+O or File->Open File
                        |For exit from this program - Press Ctrl+Q or File->Exit
                    """.trimMargin()
                    font = Font.font(30.0)
                }
            }
            row {
                paddingAll = 20.0
                label {
                    alignment = Pos.CENTER
                    text = "Visual Tokens"
                    font = Font.font(30.0)
                }
            }
            row {
                paddingAll = 20.0
                label {
                    alignment = Pos.BOTTOM_CENTER
                    text = "Created by snaulX in 2020"
                    font = Font.font(30.0)
                }
            }
        }
    }
}
