package main

import java.awt.Image
import java.awt.PopupMenu
import java.awt.SystemTray
import java.awt.TrayIcon
import java.awt.event.MouseListener
import javax.swing.ImageIcon

class QTray(label: String, image: String, popup: PopupMenu, listener: MouseListener) {

    private val trayIcon: TrayIcon
    private val tray: SystemTray

    init {
        if (!SystemTray.isSupported()) {
            throw Exception("SystemTray is not supported")
        }
        tray = SystemTray.getSystemTray()
        trayIcon = TrayIcon(createImage(image, "tray icon")!!)
        trayIcon.apply {
            isImageAutoSize = true
            toolTip = label
            addMouseListener(listener)
            popupMenu = popup
            tray.add(this)
        }
    }

    fun exit() {
        tray.remove(trayIcon)
    }

    private fun createImage(path: String, description: String): Image? {
        val imageURL = this::class.java.classLoader.getResource(path)

        return if (imageURL == null) {
            System.err.println("Resource not found: $path")
            null
        } else {
            ImageIcon(imageURL, description).image
        }
    }

}