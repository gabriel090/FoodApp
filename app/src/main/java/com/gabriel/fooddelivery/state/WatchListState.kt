package com.gabriel.fooddelivery.state


import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.gabriel.fooddelivery.model.FoodModel

data class WatchListState(
        val food: Async<List<FoodModel>> = Uninitialized
) : MvRxState
