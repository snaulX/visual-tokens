package com.snaulX.VisualTokens.views

import com.snaulX.VisualTokens.app.*
import com.snaulX.VisualTokens.app.Parser.toBlocks
import com.snaulX.VisualTokens.blocks.*
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class Worksheet() : Fragment("Visual Tokens Worksheet") {

    val clearBlocks: () -> Unit = {
        dialog("Do you want clear all blocks?") {
            paddingAll = 12.0
            button("OK").action {
                blocks.clear()
                refresh()
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
        val ws: Worksheet? = FileWorker.open()
        if (ws != null)
            replaceWith(ws)
    }
    val saveFile = {
        this.title = """${FileWorker.save(this)} - Visual Tokens"""
    }
    val exit = {
        this.close()
    }
    val paste = {
        blocks.addAll(clipboard.string.toByteArray().toBlocks())
        refresh()
    }
    @ExperimentalStdlibApi
    val addBlock: () -> Unit = {
        dialog("Select block for add") {
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
                    refresh()
                    this@dialog.close()
                }
                button("Cancel").action {
                    if (selected.value != null) blocks.removeLast()
                    this@dialog.close()
                }
            }
        }
    }
    val copy = {
        val bl: Block? = blocks.firstOrNull { it.select }
        if (bl != null) {
            clipboard.setContent {
                putString(bl.toBytes().toString())
            }
        }
    }
    val duplicate = {
        copy()
        paste()
    }
    val removeBlock = {
        val bl: Block? = blocks.firstOrNull {
            it.select
        }
        if (bl != null) {
            dialog("Do you want delete block?") {
                paddingAll = 12.0
                button("OK").action {
                    blocks.remove(bl)
                    refresh()
                    this.close()
                }
                button("Cancel").action {
                    this.close()
                }
            }
        }
    }
    var blocksUI = vbox()
    val blocks: MutableList<Block> = mutableListOf()

    fun refresh() {
        blocksUI.children.clear()
        for (block in blocks) {
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
                    item("Duplicate", "ctrl+d").action(duplicate)
                    item("Add Block", "ctrl+shift+n").action(addBlock)
                    item("Remove Block", "delete").action(removeBlock)
                    item("Clear Blocks", "ctrl+delete").action(clearBlocks)
                }
            }
        }
        row {
            buttonbar {
                paddingAll = 20.0
                button("Compile") {
                    shortcut("F9")
                    action { Parser.compile(this@Worksheet) }
                }
                button("Run") {
                    shortcut("F5")
                    action { Parser.run(this@Worksheet) }
                }
                button("Compile & Run") {
                    shortcut("F11")
                    action {
                        Parser.compile(this@Worksheet)
                        Parser.run(this@Worksheet)
                    }
                }
            }
        }
        row {
            blocksUI = vbox {
                setOnContextMenuRequested {
                    contextmenu {
                        item("Add").action(addBlock)
                        item("Delete").action(removeBlock)
                        separator()
                        item("Copy").action(copy)
                        item("Paste").action(paste)
                        item("Duplicate").action(duplicate)
                    }
                }
            }
        }
    }

    init {
        title = "Visual Tokens Worksheet"
        refresh()
    }

    constructor(title: String) : this() {
        this.title = "$title - Visual Tokens"
    }
}
