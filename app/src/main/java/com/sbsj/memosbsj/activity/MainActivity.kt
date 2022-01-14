package com.sbsj.memosbsj.activity


import DepthPageTransformer
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sbsj.memosbsj.R
import com.sbsj.memosbsj.adapter.ViewPagerAdapter
import com.sbsj.memosbsj.data.DataPage



class MainActivity : AppCompatActivity() {

    lateinit var topViewPager2 : ViewPager2
    lateinit var tabLayout :TabLayout
    var zoomOutPageTransformer = DepthPageTransformer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        topViewPager2 = findViewById(R.id.top_viewpager2)
        tabLayout =findViewById(R.id.tabLayout)
        init()

    }

    fun init()
    {

        val list : ArrayList<DataPage> = ArrayList();
        list.add(DataPage(Color.BLUE,"1 Page"));
        list.add(DataPage(Color.CYAN,"2 Page"));
        list.add(DataPage(Color.MAGENTA,"3 Page"));

        topViewPager2.adapter = ViewPagerAdapter(list)


        topViewPager2.orientation=ViewPager2.ORIENTATION_HORIZONTAL
        topViewPager2.setPageTransformer(zoomOutPageTransformer)
        TabLayoutMediator(tabLayout, topViewPager2) { tab, position ->
            //Some implementation
        }.attach()
    }

}