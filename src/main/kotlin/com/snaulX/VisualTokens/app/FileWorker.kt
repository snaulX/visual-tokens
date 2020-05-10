package com.snaulX.VisualTokens.app

import com.snaulX.VisualTokens.blocks.*
import com.snaulX.VisualTokens.operations.PlusOperator
import com.snaulX.VisualTokens.views.MainView
import com.snaulX.VisualTokens.views.Worksheet
import javafx.stage.FileChooser
import tornadofx.*
import java.io.*

object FileWorker {
    val fileChooser: FileChooser = FileChooser()

    init {
        fileChooser.extensionFilters.add(
                FileChooser.ExtensionFilter("Visual Tokens File (*.vtf)", "*.vtf")
        )
    }

    fun save(work: Worksheet): String {
        fileChooser.initialFileName = work.title.removeSuffix(" - Visual Tokens")
        return try {
            val file: File = fileChooser.showSaveDialog(work.currentWindow)
            val writer = DataOutputStream(FileOutputStream(file))
            for (block in work.blocks) {
                writer.write(block.toBytes())
            }
            file.absolutePath
        } catch (e: Exception) {
            println(e)
            "Untitled"
        }
    }

    fun open(): Worksheet? {
        return try {
            val file: File = fileChooser.showOpenDialog(find(MainView::class).currentWindow)
            val bytes: ByteArray = DataInputStream(FileInputStream(file)).readBytes()
            val blocks: MutableList<Block> = mutableListOf()
            run {
                var curBlock: Block? = null
                for (b in bytes) {
                    if (curBlock == null) {
                        when (b.toInt()) {
                            0 -> curBlock = PrintBlock()
                            1 -> curBlock = VariableBlock()
                        }
                    } else {
                        when (curBlock) {
                            is PrintBlock -> {}
                            is VariableBlock -> {}
                            is PlusOperator -> {}
                        }
                    }
                }
            } //parse bytes
            val worksheet: Worksheet = Worksheet(title = file.absolutePath)
            worksheet.blocks.addAll(blocks)
            worksheet
        } catch (e: Exception) {
            println(e)
            null
        }
    }
}