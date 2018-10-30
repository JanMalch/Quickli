package main

import java.awt.Menu
import java.awt.MenuItem
import java.io.File

data class QMenu(val title: String,
                 val children: List<QMenu>? = null,
                 val command: String? = null,
                 val directory: String? = null,
                 val separator: Boolean = false) {

    fun toMenuItem(): MenuItem {
        return if (children != null) {
            val menuItem = Menu(title)
            children.stream().forEach { menuItem.add(it) }
            menuItem
        } else {
            val menuItem = MenuItem(title)
            menuItem.addActionListener {
                if (directory != null) {
                    Runtime.getRuntime().exec(command, null, File(directory))
                } else {
                    Runtime.getRuntime().exec(command)
                }
            }
            menuItem
        }
    }
}

