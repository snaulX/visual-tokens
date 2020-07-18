package com.snaulX.VisualTokens.views

import tornadofx.App

class MainApp : App(MainView::class)

    fun main(args: Array<String>) {
        tornadofx.launch<MainApp>(args)
    }
