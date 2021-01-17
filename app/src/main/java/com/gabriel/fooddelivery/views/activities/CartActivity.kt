package com.gabriel.fooddelivery.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gabriel.fooddelivery.R
import com.gabriel.fooddelivery.views.fragments.CartFragment

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_one)


        val allMoviesFragment = CartFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, allMoviesFragment)
        transaction.commit()
    }
}