package com.example.dialogcheckinternetconnection

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TabHost
import android.widget.TableLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.dialogcheckinternetconnection.Adapter.OnBoardingViewPagerAdapter
import com.example.dialogcheckinternetconnection.Model.OnBoardingData
import com.google.android.material.tabs.TabLayout
import java.util.*
import kotlin.collections.ArrayList
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener as OnTabSelectedListener1

class MainActivity : AppCompatActivity() {
    var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter? = null
    var tabLayout: TabLayout? = null
    var onBoaViewPager: ViewPager? = null
    var next: TextView? = null
    var poistion = 0
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (restorePrefData()) {
            val i = Intent(applicationContext, MainActivity2::class.java)
            startActivity(i)
            finish()
        }
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tab_indicatom)
        next = findViewById(R.id.txt_next)
        val onBoardingData: MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(OnBoardingData("Learn anytime \n" +
                "and anywhere", "Quarantine is the perfect time to spend your\n" +
                " day learning something new, from anywhere!\n", R.drawable.splach1))
        onBoardingData.add(OnBoardingData("Find a course\n" +
                "for you", "Quarantine is the perfect time to spend your\n" +
                " day learning something new, from anywhere!", R.drawable.splach2))
        onBoardingData.add(OnBoardingData("Improve your skills", "Quarantine is the perfect time to spend your\n" +
                " day learning something new, from anywhere!", R.drawable.splach3))
        setOnBoardingViewPagerAdapter(onBoardingData)


        poistion = onBoaViewPager!!.currentItem
        next?.setOnClickListener {
            if (poistion < onBoardingData.size) {
                poistion++
                onBoaViewPager!!.currentItem = poistion
            }
            if (poistion == onBoardingData.size) {
                savePrefData()
                val i = Intent(applicationContext, MainActivity2::class.java)
                startActivity(i)

            }
        }
        tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener1 {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                poistion = tab!!.position
                if (tab.position == onBoardingData.size - 1) {
                    next!!.text = "Get Started"
                } else {
                    next!!.text = "Next"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData>) {
        onBoaViewPager = findViewById(R.id.screenPager)
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, onBoardingData)
        onBoaViewPager!!.adapter = onBoardingViewPagerAdapter
        tabLayout?.setupWithViewPager(onBoaViewPager)

    }

    private fun savePrefData() {
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
        editor.putBoolean("isFirstTimeRun", true)
        editor.apply()
    }

    private fun restorePrefData(): Boolean {
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return sharedPreferences!!.getBoolean("isFirstTimeRun", false)
    }
}