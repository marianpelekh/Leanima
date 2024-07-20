package com.leanima.database.entity

data class Item(
    val id: Int,
    val type: String,
    val name: String,
    val description: String,
    val price: Int,
    val discount: Int,
    val imageUrl: String,
    val size: String
)
