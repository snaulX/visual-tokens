package com.snaulX.VisualTokens.views

import com.snaulX.VisualTokens.app.*
import com.snaulX.VisualTokens.blocks.*
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class Worksheet() : Fragment("Visual Tokens Worksheet") {

    val allBlocks: List<String> = listOf("Print Block")
    val newFile: () -> Unit = {
        replaceWith(Worksheet("Untitled"))
    }
    val openFile = {
    }
    val saveFile = {
        FileWorker.save(this)
    }
    val exit = {
        this.close()
    }
    val copy = {
    }
    val paste = {
    }
    @ExperimentalStdlibApi
    val addBlock: () -> Unit = {
        dialog("Choose block for add") {
            val selected = SimpleStringProperty()
            combobox(selected, allBlocks).setOnAction {
                blocks.add(
                        when (selected.value) {
                            "Print Block" -> PrintBlock()
                            else -> throw Exception("Selected unknown block")
                        }
                )
            }
            hbox {
                paddingAll = 12.0
                button("OK").action {
                    updateBlocks()
                    this@dialog.close()
                }
                button("Cancel").action {
                    if (selected.value != null) blocks.removeLast()
                    this@dialog.close()
                }
            }
        }
    }
    val removeBlock = {
    }
    val blocksUI = vbox()
    val blocks: MutableList<Block> = mutableListOf()

    fun updateBlocks() {
        blocksUI.clear()
        for (block: Block in blocks) {
            blocksUI.add(block.root)
        }
    }

    @ExperimentalStdlibApi
    override val root = gridpane {
        row {
            menubar {
                menu("File") {
                    item("New File", "ctrl+n").action(newFile)
                    item("Open File", "ctrl+o").action(openFile)
                    item("Save File", "ctrl+s").action(saveFile)
                    separator()
                    item("Close Program", "ctrl+q").action(exit)
                }
                menu("Edit") {
                    item("Copy", "ctrl+c").action(copy)
                    item("Paste", "ctrl+v").action(paste)
                    item("Add Block", "ctrl+a").action(addBlock)
                    item("Remove Block", "delete").action(removeBlock)
                }
            }
        }
        row {
            buttonbar {
                paddingAll = 20.0
                button("Compile") {
                    shortcut("F9")
                }
                button("Run") {
                    shortcut("F5")
                    action { Parser.run(this@Worksheet) }
                }
                button("Compile & Run") {
                    shortcut("F11")
                }
            }
        }
        row {
            this += blocksUI
        }
    }

    init {
        updateBlocks()
    }

    constructor(title: String) : this() {
        this.title = title
    }
}
