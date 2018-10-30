package main

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.File
import java.io.FileReader

class IOManager {

    private val gson = Gson()
    private val root = """${System.getenv("APPDATA")}${File.separator}Quickli${File.separator}"""
    private val fileName = "menu.json"
    private val filePath = root + fileName
    val file = File(filePath)

    fun read(): Array<QMenu> {
        ensure()
        return gson.fromJson(JsonReader(FileReader(filePath)), Array<QMenu>::class.java)
    }

    private fun ensure() {
        val dir = File(root)
        if (!dir.exists()) {
            dir.mkdir()
        }
        if (!file.exists()) {
            file.createNewFile()
            file.writeText("[]")
        }
    }

}
