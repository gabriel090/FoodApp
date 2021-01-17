package com.gabriel.fooddelivery.utility

import android.app.Application
import com.gabriel.fooddelivery.repo.DrinksRepo
import com.gabriel.fooddelivery.repo.FoodlistRepository
import com.gabriel.fooddelivery.repo.SushiRepo


class WatchlistApp : Application() {
    val foodlistRepository by lazy {
        FoodlistRepository()
    }

    val sushiRepository by lazy {
        SushiRepo()
    }



    val drinksiRepository by lazy {
        DrinksRepo()
    }
}