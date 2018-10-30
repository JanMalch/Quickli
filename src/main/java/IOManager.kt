package main

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.File
import java.io.FileReader
import java.util.*
import kotlin.streams.toList

class IOManager {

    companion object {
        val root = """${System.getenv("APPDATA")}${File.separator}Quickli${File.separator}"""
        val images = root + "images" + File.separator
        val defaultImg = "images/icon.png"
    }

    // val root = """${System.getenv("APPDATA")}${File.separator}Quickli${File.separator}"""
    private val fileName = "menu.json"
    private val filePath = root + fileName
    val file = File(filePath)

    fun getAllFiles(): List<File> {
        return Arrays.stream(File(root).listFiles()).filter { it.isFile }.toList()
    }

}
