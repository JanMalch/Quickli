package main

import java.awt.Menu
import java.awt.MenuItem
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent

fun Menu.add(label: String, onClick: ((l: ActionEvent) -> Unit)) {
    val menu = MenuItem(label)
    menu.addActionListener(onClick)
    this.add(menu)
}

fun Menu.add(qMenu: QMenu) {
    if (qMenu.separator) {
        this.addSeparator()
    } else {
        this.add(qMenu.toMenuItem())
    }
}

// TODO: save
val String.keyCode: Int
    get() {
        val letter = this.substring(0, 1).toUpperCase()
        val code = "VK_$letter"
        println(code)
        val f = KeyEvent::class.java.getField(code)
        return f.getInt(null)
    }