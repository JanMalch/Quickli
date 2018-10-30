package main

import java.awt.Frame
import java.awt.Window

class HiddenFrame(name: String = "") : Frame(name) {
    init {
        this.isUndecorated = true
        this.type = Window.Type.UTILITY
        this.isResizable = false
        this.isVisible = true
    }
}