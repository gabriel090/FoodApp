package com.gabriel.fooddelivery.repo

import com.gabriel.fooddelivery.model.FoodModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class DrinksRepo {

    private val foods = mutableListOf<FoodModel>()


    fun addToCart(movieId: Long): Observable<FoodModel> {
        return Observable.fromCallable {
            val movie = foods.first { movie -> movie.id == movieId }
            movie.copy(isSelected = true)
        }
    }



    fun removeFromCart(movieId: Long): Observable<FoodModel> {
        return Observable.fromCallable {
            val movie = foods.first { movie -> movie.id == movieId }
            movie.copy(isSelected = false)
        }
    }



    fun getDrinks(): Observable<List<FoodModel>> = Observable.fromCallable<List<FoodModel>> {
        Thread.sleep(3000)
        foods.addAll(listOf(
                FoodModel(
                        1,
                        "Strawberry Limeade",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet dictum sit amet justo donec enim",
                        "150 grams, 4pcs",
                        "https://images.pexels.com/photos/605408/pexels-photo-605408.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                        false,
                        30

                ),
                FoodModel(
                        1235,
                        "Strawberry Limeade",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet dictum sit amet justo donec enim",
                        "180 grams, 4pcs",
                        "https://images.pexels.com/photos/338713/pexels-photo-338713.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                        false,
                        50
                ),
                FoodModel(
                        1236,
                        "Raspberry",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet dictum sit amet justo donec enim",
                        "150 grams, 4pcs",
                        "https://images.pexels.com/photos/109275/pexels-photo-109275.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                        false,
                        100
                ),
                FoodModel(
                        1237,
                        "Watermelon",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet dictum sit amet justo donec enim",
                        "150 grams, 5pcs",
                        "https://images.pexels.com/photos/1304542/pexels-photo-1304542.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                        false,
                        35
                ),
                FoodModel(
                        1238,
                        "Peach Iced",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet dictum sit amet justo donec enim",
                        "120 grams, 4pcs",
                        "https://images.pexels.com/photos/1304541/pexels-photo-1304541.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                        false,
                        60
                ),
                FoodModel(
                        1239,
                        "Sparkling",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet dictum sit amet justo donec enim",
                        "150 grams, 4pcs",
                        "https://images.pexels.com/photos/2336667/pexels-photo-2336667.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                        false,
                        70
                )

        ))
        foods

    }.subscribeOn(Schedulers.io())



    // add method to watchlist a movie

    // add method to remove a movie from watchlist

}