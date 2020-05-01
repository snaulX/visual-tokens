package com.snaulX.VisualTokens.views

import com.snaulX.VisualTokens.app.Block
import com.snaulX.VisualTokens.app.FileWorker
import com.snaulX.VisualTokens.blocks.*
import tornadofx.*

class Worksheet() : Fragment("Visual Tokens Worksheet") {

    val newFile = {
        replaceWith(Worksheet("Untitled"))
        println() //why lambda wanna return last expression and I must write this shit
    }
    val openFile = {
    }
    val saveFile = {
        FileWorker.save(this)
    }
    val exit = {
        this.close()
    }

    val blocks: MutableList<Block> = mutableListOf()

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
            StringBlock().root
        }
    }

    init {
    }

    constructor(title: String) : this() {
        this.title = title
    }
}
