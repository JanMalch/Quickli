package main

import java.io.File

data class JsonTray(val label: String,
                    val showDefaults: Boolean?,
                    val image: String?,
                    val leftClick: QCommand?,
                    val content: Array<QMenu>)

data class QCommand(val command: String,
                    val envp: Array<String>?,
                    val dir: String?) {

    fun execute() {
        if (dir != null) {
            Runtime.getRuntime().exec(command, envp, File(dir))
        } else {
            Runtime.getRuntime().exec(command, envp)
        }
    }

}

