package com.snaulX.VisualTokens.views

import com.snaulX.VisualTokens.app.*
import com.snaulX.VisualTokens.blocks.*
import javafx.beans.property.SimpleStringProperty
import javafx.scene.layout.HBox
import tornadofx.*

class Worksheet() : Fragment("Visual Tokens Worksheet") {

    val clearBlocks: () -> Unit = {
        dialog("Do you want clear all blocks?") {
            paddingAll = 12.0
            button("OK").action {
                blocks.clear()
                blocksUI.refresh()
                this.close()
            }
            button("Cancel").action {
                this.close()
            }
        }
    }
    val allBlocks: List<String> = listOf("Print Block", "Create Variable")
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
    val paste = {
        val str = clipboard.string
        if (str.startsWith("Print "))
            blocksUI.add(PrintBlock(str.removePrefix("Print ")).root)
    }
    @ExperimentalStdlibApi
    val addBlock: () -> Unit = {
        dialog("Choose block for add") {
            val selected = SimpleStringProperty()
            combobox(selected, allBlocks)
            hbox {
                paddingAll = 12.0
                button("OK").action {
                    blocks.add(
                        when (selected.value) {
                            allBlocks[0] -> PrintBlock()
                            allBlocks[1] -> VariableBlock()
                            else -> throw Exception("Selected unknown block")
                        }
                    )
                    blocksUI.refresh()
                    this@dialog.close()
                }
                button("Cancel").action {
                    if (selected.value != null) blocks.removeLast()
                    this@dialog.close()
                }
            }
        }
    }
    val blocks: MutableList<Block> = mutableListOf()
    var blocksUI = tableview(blocks.toObservable())

    fun copy(block: Block? = null) {
        val bl: Block? = block ?: (blocks.firstOrNull { it.select })
        if (bl != null) {
            clipboard.setContent {
                putString(when (bl) {
                    is PrintBlock -> "Print ${bl.value}"
                    is VariableBlock -> "Variable ${bl.name}"
                    else -> "Unknown block"
                })
            }
        }
    }
    fun duplicate(block: Block? = null) {
        copy(block)
        paste()
    }
    fun removeBlock(block: Block? = null) {
        val bl: Block? = block ?: blocks.firstOrNull {
            it.select
        }
        if (bl != null) {
            dialog("Do you want delete block?") {
                paddingAll = 12.0
                button("OK").action {
                    blocks.remove(bl)
                    blocksUI.refresh()
                    this.close()
                }
                button("Cancel").action {
                    this.close()
                }
            }
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
                    item("Copy", "ctrl+c").action { copy() }
                    item("Paste", "ctrl+v").action(paste)
                    item("Duplicate", "ctrl+d").action { duplicate() }
                    item("Add Block", "ctrl+plus").action(addBlock)
                    item("Remove Block", "delete").action { removeBlock() }
                    item("Clear Blocks", "ctrl+minus").action(clearBlocks)
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
            blocksUI = tableview {
                items = blocks.toObservable()

                column("Block", HBox::class)

                contextmenu {
                    item("Add").action(addBlock)
                    item("Delete").action { removeBlock(selectedItem) }
                    separator()
                    item("Copy").action { copy(selectedItem) }
                    item("Paste").action(paste)
                    item("Duplicate").action { duplicate(selectedItem) }
                }
            }
        }
    }

    init {
        blocksUI.refresh()
    }

    constructor(title: String) : this() {
        this.title = title
    }
}
