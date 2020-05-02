package com.snaulX.VisualTokens.views

import com.snaulX.VisualTokens.app.Block
import com.snaulX.VisualTokens.app.FileWorker
import com.snaulX.VisualTokens.blocks.*
import tornadofx.*

class Worksheet() : Fragment("Visual Tokens Worksheet") {

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
    val addBlock: () -> Unit = {
        root.row("Choose block for add") {
            listmenu {
                PrintBlock()
                StringBlock()
            }
        }
        updateBlocks()
    }
    val removeBlock = {
    }
    val blocksUI = vbox()
    val blocks: MutableList<Block> = mutableListOf()

    fun updateBlocks() {
        for (block: Block in blocks) {
            blocksUI.add(block.root)
        }
    }

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
                button("Compile")
                button("Run")
                button("Compile & Run")
            }
        }
        row {
            blocksUI
        }
    }

    init {
        updateBlocks()
    }

    constructor(title: String) : this() {
        this.title = title
    }
}
