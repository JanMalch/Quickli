package main

import java.io.File
import java.util.*
import kotlin.streams.toList

class IOManager {

    companion object {
        val root = """${System.getenv("APPDATA")}${File.separator}Quickli${File.separator}"""
        val images = root + "images" + File.separator
        val defaultImg = "images/icon.png"
    }

    fun getAllFiles(): List<File> {
        return Arrays.stream(File(root).listFiles()).filter { it.isFile }.toList()
    }

    fun ensure() {
        ensureDir(root)
        ensureDir(images)
        ensureDummyFile("example.json")
    }

    private fun ensureDir(path: String) {
        val dir = File(path)
        if (!dir.exists()) {
            dir.mkdir()
        }
    }

    private fun ensureDummyFile(path: String) {
        val file = File(root + path)
        if (!file.exists()) {
            file.writeText("""
{
   "label":"Quickli",
   "content":[
      {
         "title":"Click 'About Quickli' to find examples"
      }
   ]
}
        """.trimIndent())
        }
    }

}
