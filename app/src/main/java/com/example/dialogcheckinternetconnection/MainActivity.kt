package com.example.dialogcheckinternetconnection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.dialogcheckinternetconnection.Adapter.OnBoardingViewPagerAdapter
import com.example.dialogcheckinternetconnection.Model.OnBoardingData
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter? = null
    var tabLayout: TabLayout? = null
    var onBoaViewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tab_indicatom)
        val onBoardingData:MutableList<OnBoardingData> =ArrayList()
        onBoardingData.add(OnBoardingData("Learn anytime \n" +
                "and anywhere","Quarantine is the perfect time to spend your\n" +
                " day learning something new, from anywhere!\n",R.drawable.splach1))
        onBoardingData.add(OnBoardingData("Find a course\n" +
                "for you","Quarantine is the perfect time to spend your\n" +
                " day learning something new, from anywhere!",R.drawable.splach2))
        onBoardingData.add(OnBoardingData("Improve your skills","Quarantine is the perfect time to spend your\n" +
                " day learning something new, from anywhere!",R.drawable.splach3))
        setOnBoardingViewPagerAdapter(onBoardingData)

    }

    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData>) {
        onBoaViewPager = findViewById(R.id.screenPager)
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, onBoardingData)
        onBoaViewPager!!.adapter = onBoardingViewPagerAdapter
        tabLayout?.setupWithViewPager(onBoaViewPager)

    }
}