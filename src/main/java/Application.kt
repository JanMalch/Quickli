package main

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.File
import java.io.FileReader
import javax.swing.SwingUtilities
import javax.swing.UIManager

class Application(file: String) {

    init {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        SwingUtilities.invokeLater {
            createTray(fromJson(File(file)))
        }
    }

    private fun fromJson(input: File): JsonTray {
        return Gson().fromJson(JsonReader(FileReader(input)), JsonTray::class.java)
    }

    private fun createTray(input: JsonTray): QSystemTray {
        val tray = QSystemTray(input)
        println(input)
        return tray
    }

}