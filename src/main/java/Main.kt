package main

import java.awt.SystemTray

object TrayIconDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        if (!SystemTray.isSupported()) {
            throw Exception("SystemTray is not supported")
        }

        val io = IOManager()
        io.ensure()
        io.getAllFiles()
                .map { it.absolutePath }
                .map {
                    println("- $it")
                    val t = Thread { execJavaClass(QTrayProcess::class.java, it) }
                    t.start()
                    t
                }.forEach { it.join() }
    }

}