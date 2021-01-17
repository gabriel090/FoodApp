package com.gabriel.fooddelivery.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gabriel.fooddelivery.R
import com.gabriel.fooddelivery.model.FoodModel

class CartAdapter(private val wathclistListener: WatchlistListener) :
        RecyclerView.Adapter<CartAdapter.MovieViewHolder>() {

    private val foods = mutableListOf<FoodModel>()

    var context: Context?=null;

    fun setfoods(foods: List<FoodModel>) {
        this.foods.clear()
        this.foods.addAll(foods)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        context=parent.context;
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cart_item_list, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = foods.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val food = foods[position]

        Glide
                .with(holder.itemView)
                .load(food.imageUrl)
                .centerCrop()
                .into(holder.posterImageView)

        holder.foodTitleTextView.text = food.title
        holder.fooodPrice.text ="$ ${ food.price}"


        holder.cancelBtn.setOnClickListener {
            Toast.makeText(context, "Item removed from cart", Toast.LENGTH_SHORT).show()

            wathclistListener.removeFromCart(position)

        }
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterImageView: ImageView = itemView.findViewById(R.id.product_img)
        val foodTitleTextView: TextView = itemView.findViewById(R.id.product_title)
        val fooodPrice: TextView = itemView.findViewById(R.id.price)
        val cancelBtn: ImageView = itemView.findViewById(R.id.cancel_btn)


    }

    interface WatchlistListener {


        fun removeFromCart(movieId: Int)
    }

}