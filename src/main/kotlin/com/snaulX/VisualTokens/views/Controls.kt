package com.snaulX.VisualTokens.views

import com.snaulX.VisualTokens.app.Parser
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.TextField
import javafx.scene.layout.Pane
import tornadofx.*

private val win: Worksheet
    get() = find(Worksheet::class)

val operators: List<String> = listOf("+")

fun Pane.textvaluefield(text: String?): TextField {
    return textfield(text) {
        contextmenu {
            item("Add Variable").action {
                win.dialog("Select variable") {
                    val selected = textfield("Write name of variable")
                    hbox {
                        paddingAll = 12.0
                        button("OK").action {
                            this@textfield.text += "vtvar_${selected.text}?"
                            this@dialog.close()
                        }
                        button("Cancel").action {
                            this@dialog.close()
                        }
                    }
                }
            }
            item("Add Operator").action {
                win.dialog("Select operator") {
                    val selected = SimpleStringProperty()
                    combobox(selected, operators)
                    val first = textvaluefield("First value")
                    val second = textvaluefield("Second value")
                    hbox {
                        paddingAll = 12.0
                        button("OK").action {
                            this@textfield.text +=
                                "vtop_${selected.get()}!${first.text}!${second.text}!"
                            this@dialog.close()
                        }
                        button("Cancel").action {
                            this@dialog.close()
                        }
                    }
                }
            }
        }
    }
}