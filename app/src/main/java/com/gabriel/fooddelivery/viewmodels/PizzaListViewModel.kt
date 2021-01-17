package com.gabriel.fooddelivery.viewmodels

import androidx.lifecycle.MutableLiveData
import com.airbnb.mvrx.*
import com.gabriel.fooddelivery.repo.FoodlistRepository
import com.gabriel.fooddelivery.state.WatchListState
import com.gabriel.fooddelivery.utility.WatchlistApp


class PizzaListViewModel(
        initialState: WatchListState,
        private val foodlistRepository: FoodlistRepository
) : BaseMvRxViewModel<WatchListState>(initialState, debugMode = true) {

    val errorMessage = MutableLiveData<String>()

    init {
        // 1
        setState {
            copy(food = Loading())
        }


        // 2
        foodlistRepository.getAllpizzas()
                .execute {
                    copy(food = it)
                }

        val errorMessage = MutableLiveData<String>()

    }
    fun addToCart(movieId: Long) {
        withState { state ->
            if (state.food is Success) {
                val index = state.food.invoke().indexOfFirst {
                    it.id == movieId
                }
                // 1
                foodlistRepository.addToCart(movieId)
                        .execute {
                            // 2
                            if (it is Success) {
                                copy(
                                        food = Success(
                                                state.food.invoke().toMutableList().apply {
                                                    set(index, it.invoke())
                                                }
                                        )
                                )
                                // 3
                            } else if (it is Fail){
                                errorMessage.postValue("Failed to add movie to watchlist")
                                copy()
                            } else {
                                copy()
                            }
                        }
            }
        }
    }

    fun removeFromCart(movieId: Long) {
        withState { state ->
            if (state.food is Success) {
                val index = state.food.invoke().indexOfFirst {
                    it.id == movieId
                }
                foodlistRepository.removeFromCart(movieId)
                        .execute {
                            if (it is Success) {
                                copy(
                                        food = Success(
                                                state.food.invoke().toMutableList().apply {
                                                    set(index, it.invoke())
                                                }
                                        )
                                )
                            } else if (it is Fail) {
                                errorMessage.postValue("Failed to remove movie from watchlist")
                                copy()
                            } else {
                                copy()
                            }
                        }
            }
        }
    }

    companion object : MvRxViewModelFactory<PizzaListViewModel, WatchListState> {

        override fun create(viewModelContext: ViewModelContext,
                            state: WatchListState): PizzaListViewModel? {
            val foodlistRepository =
                    viewModelContext.app<WatchlistApp>().foodlistRepository
            return PizzaListViewModel(state, foodlistRepository)
        }
    }
}
