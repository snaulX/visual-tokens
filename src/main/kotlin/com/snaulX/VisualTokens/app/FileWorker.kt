package com.snaulX.VisualTokens.app

import com.snaulX.VisualTokens.views.Worksheet
import javafx.stage.FileChooser
import java.io.DataOutputStream
import java.io.File
import java.io.FileOutputStream

object FileWorker {
    fun save(work: Worksheet): String {
        val fileChooser = FileChooser()
        fileChooser.initialFileName = work.title.removeSuffix(" - Visual Tokens")
        fileChooser.extensionFilters.add(
                FileChooser.ExtensionFilter("Visual Tokens File (*.vtf)", "*.vtf")
        )
        return try {
            val file: File = fileChooser.showSaveDialog(work.currentWindow)
            val writer = DataOutputStream(FileOutputStream(file))
            for (block in work.blocks) run {
                writer.write(block.toBytes())
            }
            file.absolutePath
        } catch (e: Exception) {
            println(e)
            "Untitled"
        }
    }
}