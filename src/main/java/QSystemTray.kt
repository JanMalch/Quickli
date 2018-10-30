package main

import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.io.File
import java.net.URI
import javax.imageio.ImageIO
import javax.swing.ImageIcon

class QSystemTray(jsonTray: JsonTray) {

    private val tray: SystemTray = SystemTray.getSystemTray()
    private val trayIcon: TrayIcon
    private val frame = HiddenFrame()

    private val defaultImage: Image =
            ImageIcon(this::class.java.classLoader.getResource(IOManager.defaultImg), "tray icon").image

    private var popup: PopupMenu? = null
        get() = trayIcon.popupMenu

    init {
        val img = if (jsonTray.image != null) createImage(jsonTray.image) else defaultImage
        trayIcon = TrayIcon(img)
        trayIcon.apply {
            isImageAutoSize = true
            toolTip = jsonTray.label
            addMouseListener(object : MouseAdapter() {
                override fun mouseClicked(e: MouseEvent) {
                    if (e.button == MouseEvent.BUTTON1) {
                        if (jsonTray.leftClick != null) {
                            jsonTray.leftClick.execute()
                        } else {
                            openPopup(e)
                        }
                    }
                }
            })

            popupMenu = initPopup(jsonTray)
            tray.add(this)
        }
    }

    private fun initPopup(jsonTray: JsonTray): PopupMenu {
        val popup = PopupMenu()

        jsonTray.content.forEach { popup.add(it) }

        popup.addSeparator()
        if (jsonTray.showDefaults == null || jsonTray.showDefaults) {
            setupDefaults()
        }
        popup.add("Exit") {
            exit()
        }

        return popup
    }

    private fun setupDefaults() {
        popup?.add("Edit entries") {
            Desktop.getDesktop().open(File(IOManager.root))
        }

        popup?.add("About Quickli") {
            Desktop.getDesktop().browse(URI("https://github.com/JanMalch/Quickli"))
        }
    }

    fun openPopup(e: MouseEvent) {
        frame.add(popup)
        popup?.show(frame, e.xOnScreen, e.yOnScreen)
    }

    fun exit() {
        tray.remove(trayIcon)
        System.exit(0)
    }

    private fun createImage(name: String): Image? {
        return ImageIO.read(File(IOManager.images + name))
    }

}