package com.kiosk.menu.drink

import com.kiosk.menu.Menu

open class Drink(name: String, price: Int, description: String) : Menu(name, description) {
    val price: Int

    init {
        this.price = price
    }

    override fun displayInfo() {
        println(String.format("%-25s | W %.1f | %s", name, price.toDouble() / 1000, description))
    }
}