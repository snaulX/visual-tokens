package com.snaulX.VisualTokens.app

import com.snaulX.VisualTokens.app.Parser.toBlocks
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
            file.nameWithoutExtension
        } catch (e: Exception) {
            println(e)
            "Untitled"
        }
    }

    fun open(): Worksheet? {
        return try {
            val file: File = fileChooser.showOpenDialog(find(MainView::class).currentWindow)
            val bytes: ByteArray = DataInputStream(FileInputStream(file)).readBytes()
            val worksheet: Worksheet = Worksheet(title = file.absolutePath)
            worksheet.blocks.addAll(bytes.toBlocks())
            worksheet
        } catch (e: Exception) {
            println(e)
            null
        }
    }
}