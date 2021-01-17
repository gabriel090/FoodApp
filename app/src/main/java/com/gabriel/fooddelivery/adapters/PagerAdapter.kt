package com.gabriel.fooddelivery.adapters
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gabriel.fooddelivery.views.fragments.AllFoodFragment
import com.gabriel.fooddelivery.views.fragments.DrinksFragment
import com.gabriel.fooddelivery.views.fragments.SushiFragment

class PagerAdapter(activity: AppCompatActivity, val itemsCount: Int) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return itemsCount
    }
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 ->
                return  AllFoodFragment();
            1->
                return SushiFragment();
            2 ->
                return DrinksFragment();
        }
        return    return AllFoodFragment()


    }
}

