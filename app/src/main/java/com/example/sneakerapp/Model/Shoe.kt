package com.example.sneakerapp.Model

data class Shoe(
    val name: String?=null,
    val brand: String?=null,
    val price: Double?=null,
    val imageResourceId: Int?=null,
    val variations: List<Shoe> = listOf()
)
