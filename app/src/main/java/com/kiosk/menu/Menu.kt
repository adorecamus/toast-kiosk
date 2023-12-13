package com.kiosk.menu

open class Menu(name: String, description: String) {
    val name: String
    val description: String

    init {
        this.name = name
        this.description = description
    }

    open fun displayInfo() {
        println(String.format("%-12s | %-12s", name, description))
    }
}