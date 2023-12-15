package com.kiosk

open class Menu(name: String, description: String) {
    val name: String
    val description: String
    val items = ArrayList<Item>()

    init {
        this.name = name
        this.description = description
    }

    open fun displayInfo() {
        println(String.format("%-8s | %-12s", name, description))
    }
}