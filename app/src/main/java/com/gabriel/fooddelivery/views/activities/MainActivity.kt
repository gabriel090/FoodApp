package com.gabriel.fooddelivery.views.activities


import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

import com.google.android.material.tabs.TabLayoutMediator
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.scroll_layout.*

import com.gabriel.fooddelivery.R
import com.gabriel.fooddelivery.adapters.PagerAdapter
import com.gabriel.fooddelivery.adapters.SliderAdapter
import com.gabriel.fooddelivery.model.SliderItem


class MainActivity : AppCompatActivity() {

    private lateinit var foodNamesArray: Array<String>
    // var sliderItemList = mutableListOf<SliderItem>()



    //TODO:4 Define page change callback here

    var doppelgangerPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            /*     Toast.makeText(
                     this@MainActivity, "Selected position: ${position}",
                     Toast.LENGTH_SHORT
                 ).show()*/
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

// In Activity's onCreate() for instance
        //transparen status bar support
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }



        //code for image slider at the top of the activity
        slderImageMethod(this)



        foodNamesArray = resources.getStringArray(R.array.food_names)

        //TODO:3 Wire DoppelgangerAdapter with ViewPager2 here
        val doppelgangerAdapter = PagerAdapter(this, foodNamesArray.size)
        doppelgangerViewPager.adapter = doppelgangerAdapter



        //TODO:5 Register page change callback here

        doppelgangerViewPager.registerOnPageChangeCallback(doppelgangerPageChangeCallback)


        //TODO:10 Connect TabLayout and ViewPager2 here

        TabLayoutMediator(tabLayout, doppelgangerViewPager) { tab, position ->
            //To get the first name of doppelganger celebrities
            tab.text = foodNamesArray[position].split(" ")[0]
        }.attach()

    }



    fun slderImageMethod(context: Context){


        val imgSliderAdapter= SliderAdapter(context)
        imgSliderAdapter.addItem(
                SliderItem(
                        "Top sushi deals",
                        "https://images.pexels.com/photos/2098085/pexels-photo-2098085.jpeg?auto=compress&cs=tinysrgb&h=650&w=940"
                )
        )
        imgSliderAdapter.addItem(
                SliderItem(
                        "Top pizza deals",
                        "https://images.pexels.com/photos/4193872/pexels-photo-4193872.jpeg?auto=compress&cs=tinysrgb&h=650&w=940"
                )
        )
        imgSliderAdapter.addItem(
                SliderItem(
                        " consectetur adipiscing ",
                        "https://images.pexels.com/photos/50593/coca-cola-cold-drink-soft-drink-coke-50593.jpeg?auto=compress&cs=tinysrgb&h=650&w=940"
                )
        )

        imageSlider.setSliderAdapter(imgSliderAdapter)
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageSlider.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH;
        imageSlider.indicatorSelectedColor = Color.WHITE;
        imageSlider.indicatorUnselectedColor = Color.GRAY;
        imageSlider.scrollTimeInSec = 3;
        imageSlider.isAutoCycle = true;
        imageSlider.startAutoCycle();

    }
}