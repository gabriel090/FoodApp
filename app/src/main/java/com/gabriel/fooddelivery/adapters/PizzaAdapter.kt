package com.gabriel.fooddelivery.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gabriel.fooddelivery.R
import com.gabriel.fooddelivery.model.FoodModel

class PizzaAdapter(private val wathclistListener: WatchlistListener) :
        RecyclerView.Adapter<PizzaAdapter.MovieViewHolder>() {

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
                .inflate(R.layout.food_viewholder_layout, parent, false)
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
        holder.foodDesc.text = food.desc
        holder.foodWeight.text = food.weight

        if (food.isSelected) {
            holder.watchlistButton.setBackgroundColor(Color.GREEN)
            holder.watchlistButton.text="REMOVE"

        }else{
            holder.watchlistButton.setBackgroundColor(Color.BLUE)
            holder.watchlistButton.text=" $ ${food.price} "
        }




        holder.watchlistButton.setOnClickListener {
            Toast.makeText(context, "Item aadded to cart", Toast.LENGTH_SHORT).show()
            if (food.isSelected) {
                wathclistListener.removeFromWatchlist(food.id)
            } else {
                wathclistListener.addToWatchlist(food.id)
            }
        }
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterImageView: ImageView = itemView.findViewById(R.id.movie_poster_imageview)
        val foodTitleTextView: TextView = itemView.findViewById(R.id.header)
        val foodDesc: TextView = itemView.findViewById(R.id.description)
        val foodWeight: TextView = itemView.findViewById(R.id.weight)

        val watchlistButton: Button = itemView.findViewById(R.id.watchlist_button)
    }

    interface WatchlistListener {

        fun addToWatchlist(movieId: Long)

        fun removeFromWatchlist(movieId: Long)
    }

}