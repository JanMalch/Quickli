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
        return when {
            children != null -> {
                val menuItem = Menu(title)
                children.stream().forEach { menuItem.add(it) }
                menuItem
            }
            command != null -> {
                val menuItem = MenuItem(title)
                menuItem.addActionListener {
                    QCommand(command, null, directory).execute()
                }
                menuItem
            }
            else -> {
                val menuItem = MenuItem(title)
                menuItem.isEnabled = false
                menuItem
            }
        }
    }
}

