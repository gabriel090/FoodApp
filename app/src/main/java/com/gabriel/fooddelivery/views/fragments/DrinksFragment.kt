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
import com.gabriel.fooddelivery.adapters.PizzaAdapter
import com.gabriel.fooddelivery.viewmodels.DrinkListViewModel
import com.gabriel.fooddelivery.views.activities.CartActivity
import com.gabriel.fooddelivery.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_all_foods.*

class DrinksFragment : BaseMvRxFragment() {
    private lateinit var pizzaAdapter: PizzaAdapter

    lateinit var selecteCartItems: SharedPreferences
    // instantiate

    var cart_counter: Int?=0

    /*  This creates a shared ViewModel that all fragments with the same parent activity can access.
      activityViewModel
      is an extension function from MvRx that does the work for you.*/
    private val foodlistViewModel: DrinkListViewModel by activityViewModel()





//used for creating a static instance of the Fragment

    companion object {
        const val ARG_POSITION = "position"

        fun getInstance(position: Int): Fragment {
            val doppelgangerFragment = AllFoodFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            doppelgangerFragment.arguments = bundle
            return doppelgangerFragment
        }
    }







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
        return inflater.inflate(R.layout.fragment_drinks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//          val position = requireArguments().getInt(ARG_POSITION)




        //recyclerview and its adapter operations
        pizzaAdapter = PizzaAdapter(object : PizzaAdapter.WatchlistListener {
            override fun addToWatchlist(movieId: Long) {
                // call ViewModel to add movie to watchlist
                foodlistViewModel.addToCart(movieId)//the movie id values come from the adapter class
            }

            override fun removeFromWatchlist(movieId: Long) {
                // call ViewModel to remove movie from watchlist
                foodlistViewModel.removeFromCart(movieId)
            }
        })
        all_cartitems_recycler.adapter = pizzaAdapter


        cartButton.setOnClickListener {
            counter_no.isVisible=false

            val intent = Intent(this@DrinksFragment.context, CartActivity::class.java)
            startActivity(intent)
        }


    }




    //called everytime there is a change in state
    override fun invalidate() {
//    If the async call is in progress and the movies property is in Loading state, it hides the RecyclerView and shows a ProgressBar.
//    When the async call succeeds, it hides the ProgressBar and populates the RecyclerView with the movies.
//    If it fails, it hides the ProgressBar and shows a Toast with the failure message.
//    One more thing, tell the ViewModel to start fetching the list of movies from the repository.

        withState(foodlistViewModel) { state ->
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