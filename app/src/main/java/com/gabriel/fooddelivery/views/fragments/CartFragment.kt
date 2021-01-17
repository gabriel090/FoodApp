package com.gabriel.fooddelivery.views.fragments


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gabriel.fooddelivery.R
import com.gabriel.fooddelivery.adapters.CartAdapter
import com.gabriel.fooddelivery.model.FoodModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_cart.*


class CartFragment  : Fragment() {



    private lateinit var cartAdapter: CartAdapter
    lateinit var selecteCartItems_pref: SharedPreferences
    var sum=0




    // add ViewModel declaration here
    override fun onAttach(context: Context) {
        super.onAttach(context)
        //   val activity = if (context is Activity) context else null

        //initializing the shared pref
        selecteCartItems_pref= context.getSharedPreferences("CART_PREF",Context.MODE_PRIVATE)



    }



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val pref = selecteCartItems_pref.getString("food_list", null)

        val turnsType = object : TypeToken<MutableList<FoodModel>>() {}.type
        val selectedCartItems = Gson().fromJson<MutableList<FoodModel>>(pref, turnsType)




        //recyclerview and its adapter operations
        cartAdapter = CartAdapter(object : CartAdapter.WatchlistListener {
            override fun removeFromCart(movieId: Int) {

                selectedCartItems.removeAt(movieId)

                cartAdapter.setfoods(selectedCartItems)
                all_cartitems_recycler.adapter = cartAdapter

            }
        })


        for(a:FoodModel in selectedCartItems){

            sum += a.price

        }

        cartAdapter.setfoods(selectedCartItems)
        all_cartitems_recycler.adapter = cartAdapter

        progress_bar.visibility = View.GONE
        all_cartitems_recycler.visibility = View.VISIBLE


        tv_sum.text=" Tolal Amount = $ $sum"



    }





    private fun showLoader() {
        progress_bar.visibility = View.VISIBLE

        all_cartitems_recycler.visibility = View.GONE
    }

    private fun showCartItems(watchlistedMovies: List<FoodModel>) {

        Log.i("pato", "showCartItems: ${watchlistedMovies.size} ")
        if (watchlistedMovies.isEmpty()) {


            progress_bar.visibility = View.GONE

            all_cartitems_recycler.visibility = View.GONE
        } else {


            progress_bar.visibility = View.GONE

            all_cartitems_recycler.visibility = View.VISIBLE

            cartAdapter.setfoods(watchlistedMovies)
        }
    }

    private fun showError() {
        Toast.makeText(requireContext(), "Failed to load watchlist", Toast.LENGTH_SHORT).show()
    }


}