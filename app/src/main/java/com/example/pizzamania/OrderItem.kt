package com.example.pizzamaniya

data class OrderItem(
    val name: String,
    val qty: Int,
    val price: Int,
    var selected: Boolean = false
)
