package main

import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.io.File
import java.net.URI
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import kotlin.system.exitProcess

class QSystemTray(jsonTray: JsonTray) {

    private val tray: SystemTray = SystemTray.getSystemTray()
    private val trayIcon: TrayIcon
    private val frame = HiddenFrame()

    private val defaultImage: Image =
            ImageIcon(this::class.java.classLoader.getResource(IOManager.defaultImg), "tray icon").image

    private val popup: PopupMenu?
        get() = trayIcon.popupMenu

    init {
        val img = jsonTray.image?.let(this::createImage) ?: defaultImage

        trayIcon = TrayIcon(img).apply {
            isImageAutoSize = true
            toolTip = jsonTray.label
            addMouseListener(object : MouseAdapter() {
                override fun mouseClicked(e: MouseEvent) {
                    if (e.button == MouseEvent.BUTTON1) {
                        jsonTray.leftClick?.execute()
                                ?: openPopup(e)
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
        if (jsonTray.showDefaults != false) {
            setupDefaults(popup)
        }
        popup.add("Exit") {
            exit()
        }

        return popup
    }

    private fun setupDefaults(popup: PopupMenu) {
        popup.add("Edit entries") {
            Desktop.getDesktop().open(File(IOManager.root))
        }

        popup.add("About Quickli") {
            Desktop.getDesktop().browse(URI("https://github.com/JanMalch/Quickli"))
        }
    }

    fun openPopup(e: MouseEvent) {
        frame.add(popup)
        popup?.show(frame, e.xOnScreen, e.yOnScreen)
    }

    fun exit() {
        tray.remove(trayIcon)
        exitProcess(0)
    }

    private fun createImage(name: String): Image? {
        return ImageIO.read(File(IOManager.images + name))
    }

}
