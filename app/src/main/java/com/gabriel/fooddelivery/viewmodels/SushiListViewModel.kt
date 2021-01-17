package com.gabriel.fooddelivery.viewmodels


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.airbnb.mvrx.*
import com.gabriel.fooddelivery.repo.SushiRepo
import com.gabriel.fooddelivery.state.WatchListState
import com.gabriel.fooddelivery.utility.WatchlistApp

class SushiListViewModel(
        initialState: WatchListState,
        private val foodlistRepository: SushiRepo
) : BaseMvRxViewModel<WatchListState>(initialState, debugMode = true) {

    val errorMessage = MutableLiveData<String>()


    /* To modify the state, use setState(). In this case, you’re using copy() to make a copy of the current state and change the type of
     the movies property to Loading to reflect that an operation is underway.
     Then, you start fetching the list of movies from the repository. When it finishes, use the
     obtained movie list to set the new state. MvRX provides execute() as a method to convert a RxJava observable to an Async type.
 */

    init {
        // 1
        setState {
            copy(food = Loading())
        }


        // 2

        Log.i("sushi", "sushi viewmodel is executed")
        foodlistRepository.getSushis()
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

    companion object : MvRxViewModelFactory<SushiListViewModel, WatchListState> {

        override fun create(viewModelContext: ViewModelContext,
                            state: WatchListState
        ): SushiListViewModel? {
            val foodlistRepository =
                    viewModelContext.app<WatchlistApp>().sushiRepository
            return SushiListViewModel(state, foodlistRepository)
        }
    }




}
