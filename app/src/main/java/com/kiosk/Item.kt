package com.kiosk

open class Item(name: String, price: Int, description: String) : Menu(name, description) {
    val price: Int

    init {
        this.price = price
    }

    override fun displayInfo() {
        print(String.format("%-25s | W %.1f | %s", name, price.toDouble() / 1000, description))
    }
}