package com.example.dialogcheckinternetconnection.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.dialogcheckinternetconnection.Model.OnBoardingData
import com.example.dialogcheckinternetconnection.R

class OnBoardingViewPagerAdapter(private val context: Context, private val onBoardingDataList: List<OnBoardingData>) : PagerAdapter() {
    override fun getCount(): Int {
        return onBoardingDataList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.onboarder_screen_layout, null)
        val imageview: ImageView = view.findViewById(R.id.image_pager)
        val title: TextView = view.findViewById(R.id.text_title)
        val desc: TextView = view.findViewById(R.id.text_desc)

        imageview.setImageResource(onBoardingDataList[position].imageUrl)
        title.text = onBoardingDataList[position].title
        desc.text = onBoardingDataList[position].desc
        container.addView(view)
        return view


    }
}