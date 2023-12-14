package com.kiosk

class Account {
    private var balance = 0L

    fun deposit(amount: Long): Boolean {
        balance += amount
        return true
    }

    fun withdraw(amount: Long): Boolean {
        if (balance >= amount) {
            balance -= amount
            return true
        }
        return false
    }

    fun getBalance(): Long {
        return balance
    }
}