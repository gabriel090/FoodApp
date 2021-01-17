package com.gabriel.fooddelivery.repo

import com.gabriel.fooddelivery.model.FoodModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class SushiRepo {

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



    fun getSushis(): Observable<List<FoodModel>> = Observable.fromCallable<List<FoodModel>> {
        Thread.sleep(3000)
        foods.addAll(listOf(
                FoodModel(
                        1,
                        "Makizushi",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet dictum sit amet justo donec enim",
                        "150 grams, 4pcs",
                        "https://images.pexels.com/photos/357756/pexels-photo-357756.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                        false,
                        37

                ),
                FoodModel(
                        1235,
                        "Gunkan",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet dictum sit amet justo donec enim",
                        "180 grams, 4pcs",
                        "https://images.pexels.com/photos/2323398/pexels-photo-2323398.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                        false,
                        48
                ),
                FoodModel(
                        1236,
                        "Temaki",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet dictum sit amet justo donec enim",
                        "150 grams, 4pcs",
                        "https://images.pexels.com/photos/684965/pexels-photo-684965.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                        false,
                        56
                ),
                FoodModel(
                        1237,
                        "Narezushi",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet dictum sit amet justo donec enim",
                        "150 grams, 5pcs",
                        "https://images.pexels.com/photos/681586/sushi-japan-soya-rice-681586.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                        false,
                        65
                ),
                FoodModel(
                        1238,
                        "Nigiri",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet dictum sit amet justo donec enim",
                        "120 grams, 4pcs",
                        "https://images.pexels.com/photos/2098143/pexels-photo-2098143.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                        false,
                        76
                ),
                FoodModel(
                        1239,
                        "Sasazushi",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet dictum sit amet justo donec enim",
                        "150 grams, 4pcs",
                        "https://images.pexels.com/photos/1052189/pexels-photo-1052189.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                        false,
                        88
                )

        ))
        foods

    }.subscribeOn(Schedulers.io())



    // add method to watchlist a movie

    // add method to remove a movie from watchlist

}