package main

import java.awt.Desktop
import java.awt.PopupMenu
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.net.URI
import javax.swing.SwingUtilities
import javax.swing.UIManager

object TrayIconDemo {
    @JvmStatic
    fun main(args: Array<String>) {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        UIManager.put("swing.boldMetal", java.lang.Boolean.FALSE)

        SwingUtilities.invokeLater { createAndShowGUI() }
    }

    private fun createAndShowGUI() {
        val io = IOManager()
        val popup = PopupMenu()
        val frame = HiddenFrame()
        val tray = QTray("Quickli", "images/icon.png", popup, object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                if (e.button == MouseEvent.BUTTON1) {
                    frame.add(popup)
                    popup.show(frame, e.xOnScreen, e.yOnScreen)
                }
            }
        })

        io.read().forEach { popup.add(it) }
        popup.addSeparator()

        popup.add("Edit entries") {
            Desktop.getDesktop().open(io.file)
        }

        popup.add("About Quickli") {
            Desktop.getDesktop().browse(URI("https://github.com/JanMalch/Quickli"))
        }

        popup.add("Exit") {
            tray.exit()
            System.exit(0)
        }
    }
}