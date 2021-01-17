package com.gabriel.fooddelivery.repo

import com.gabriel.fooddelivery.model.FoodModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class FoodlistRepository {

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





    fun getAllpizzas(): Observable<List<FoodModel>> = Observable.fromCallable<List<FoodModel>> {
        Thread.sleep(3000)
        foods.addAll(listOf(
                FoodModel(
                        1,
                        "Margherita",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet dictum sit amet justo donec enim",
                        "150 grams, 4pcs",
                        "https://images.pexels.com/photos/2762942/pexels-photo-2762942.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=500&w=500",
                        false,
                        50

                ),
                FoodModel(
                        1235,
                        "Double Cheese",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet dictum sit amet justo donec enim",
                        "180 grams, 4pcs",
                        "https://images.pexels.com/photos/2147491/pexels-photo-2147491.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                        false,
                        40
                ),
                FoodModel(
                        1236,
                        "Farm House.",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet dictum sit amet justo donec enim",
                        "150 grams, 4pcs",
                        "https://images.pexels.com/photos/4109111/pexels-photo-4109111.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260",
                        false,
                        30
                ),
                FoodModel(
                        1237,
                        "Peppy Paneer",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet dictum sit amet justo donec enim",
                        "150 grams, 5pcs",
                        "https://images.pexels.com/photos/3762075/pexels-photo-3762075.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                        false,
                        65
                ),
                FoodModel(
                        1238,
                        "Mexican Green",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet dictum sit amet justo donec enim",
                        "120 grams, 4pcs",
                        "https://images.pexels.com/photos/3944308/pexels-photo-3944308.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                        false,
                        55
                ),
                FoodModel(
                        1239,
                        "Deluxe Veggie.",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet dictum sit amet justo donec enim",
                        "150 grams, 4pcs",
                        "https://images.pexels.com/photos/825661/pexels-photo-825661.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                        false,
                        42
                )

        ))
        foods

    }.subscribeOn(Schedulers.io())


    // add method to watchlist a movie

    // add method to remove a movie from watchlist

}