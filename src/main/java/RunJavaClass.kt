package main

import java.io.File
import java.io.IOException


@Throws(IOException::class, InterruptedException::class)
fun execJavaClass(klass: Class<*>, vararg args: String): Int {
    val javaHome = System.getProperty("java.home")
    val javaBin = javaHome +
            File.separator + "bin" +
            File.separator + "java"
    val classpath = System.getProperty("java.class.path")
    val className = klass.canonicalName
    val builder = ProcessBuilder(
            javaBin, "-cp", classpath, className, *args)

    val process = builder.start()
    process.waitFor()
    return process.exitValue()
}