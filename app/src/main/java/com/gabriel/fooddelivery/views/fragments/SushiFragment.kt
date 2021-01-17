package com.gabriel.fooddelivery.views.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.airbnb.mvrx.*
import com.gabriel.fooddelivery.R
import com.gabriel.fooddelivery.adapters.PizzaAdapter
import com.gabriel.fooddelivery.viewmodels.SushiListViewModel
import com.gabriel.fooddelivery.views.activities.CartActivity

import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_all_foods.*


class SushiFragment : BaseMvRxFragment() {
    private lateinit var pizzaAdapter: PizzaAdapter

    lateinit var selecteCartItems: SharedPreferences
    // instantiate

    var cart_counter: Int?=0

    /*  This creates a shared ViewModel that all fragments with the same parent activity can access.
      activityViewModel
      is an extension function from MvRx that does the work for you.*/
    private val sushilistViewModel: SushiListViewModel by activityViewModel()





//used for creating a static instance of the Fragment








    // add ViewModel declaration here
    override fun onAttach(context: Context) {
        super.onAttach(context)
        //   val activity = if (context is Activity) context else null

        //initializing the shared pref
        selecteCartItems= context.getSharedPreferences("CART_PREF", Context.MODE_PRIVATE)



    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sushi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)






        //recyclerview and its adapter operations
        pizzaAdapter = PizzaAdapter(object : PizzaAdapter.WatchlistListener {
            override fun addToWatchlist(movieId: Long) {
                // call ViewModel to add movie to watchlist
                sushilistViewModel.addToCart(movieId)//the movie id values come from the adapter class
            }

            override fun removeFromWatchlist(movieId: Long) {
                // call ViewModel to remove movie from watchlist
                sushilistViewModel.removeFromCart(movieId)
            }
        })
        all_cartitems_recycler.adapter = pizzaAdapter


        cartButton.setOnClickListener {
            counter_no.isVisible=false

            val intent = Intent(this@SushiFragment.context, CartActivity::class.java)
            startActivity(intent)
        }


    }




    //called everytime there is a change in state
    override fun invalidate() {

        withState(sushilistViewModel) { state ->
            when (state.food) {
                // 1
                is Loading -> {
                    progress_bar.visibility = View.VISIBLE
                    all_cartitems_recycler.visibility = View.GONE
                }
                // 2
                is Success -> {
                    progress_bar.visibility = View.GONE
                    all_cartitems_recycler.visibility = View.VISIBLE
                    pizzaAdapter.setfoods(state.food.invoke())


                    val retrievedFoodItems=state.food.invoke();

                    val editor: SharedPreferences.Editor = selecteCartItems.edit()
                    val gson = Gson()
                    val json = gson.toJson(retrievedFoodItems.filter { foodModel -> foodModel.isSelected })
                    editor.putString("food_list", json)
                    editor.apply()
                    editor.commit()

                    cart_counter =
                            retrievedFoodItems.filter { foodModel -> foodModel.isSelected }.size

                    val sharedIdValue = selecteCartItems.getString("food_list", "no data saved")

                    Log.i("mox", "data from gson is: $sharedIdValue ")


                }
                // 3
                is Fail -> {
                    Toast.makeText(
                            requireContext(),
                            "Failed to load all movies",
                            Toast.LENGTH_SHORT
                    ).show()
                }



            }

            Log.i("mato", "the value for true is: $cart_counter ")


            if (cart_counter!=0){

                counter_no.text="$cart_counter"

                counter_no.isVisible=true
            }else{
                counter_no.isVisible=false
            }

        }
    }
}