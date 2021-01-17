package com.gabriel.fooddelivery.model

data class FoodModel(
        val id: Long,
        val title: String,
        val desc: String,
        val weight: String,
        val imageUrl: String,
        val isSelected: Boolean,
        val price:Int
)