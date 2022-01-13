package com.sbsj.memosbsj.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.sbsj.memosbsj.R
import com.sbsj.memosbsj.adapter.ViewPagerAdapter
import com.sbsj.memosbsj.data.DataPage


class MainActivity : AppCompatActivity() {

    lateinit var topViewPager2 : ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        topViewPager2 = findViewById(R.id.top_viewpager2)
        init()

    }

    fun init()
    {
        var list : ArrayList<DataPage> = ArrayList();
        list.add(DataPage(android.R.color.holo_blue_bright,"1 Page"));
        list.add(DataPage(android.R.color.white,"2 Page"));
        list.add(DataPage(android.R.color.holo_orange_dark,"3 Page"));
        list.add(DataPage(android.R.color.holo_blue_dark,"4 Page"));
        topViewPager2.adapter = ViewPagerAdapter(list)
    }

}