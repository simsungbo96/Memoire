import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sbsj.memosbsj.R
import com.sbsj.memosbsj.adapter.ViewPagerAdapter
import com.sbsj.memosbsj.data.DataPage

class HomeFragment: Fragment() {

    lateinit var topViewPager2 : ViewPager2
    lateinit var tabLayout : TabLayout
    var zoomOutPageTransformer = DepthPageTransformer()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topViewPagerCreate(view);
    }

    fun topViewPagerCreate(view:View) /*상단 뷰페이저 생성*/
    {
        topViewPager2 = view.findViewById(R.id.top_viewpager2)
        tabLayout =view.findViewById(R.id.tabLayout)
        /*뷰페이저 생성 할때 초기화 함수 여기서 쓰자*/
        val list : ArrayList<DataPage> = ArrayList();
        list.add(DataPage(Color.BLUE,"1 Page"));
        list.add(DataPage(Color.CYAN,"2 Page"));
        list.add(DataPage(Color.MAGENTA,"3 Page"));
        /*~뷰페이저 생성 할때 초기화 함수 여기서 쓰자*/
        topViewPager2.adapter = ViewPagerAdapter(list)
        topViewPager2.orientation=ViewPager2.ORIENTATION_HORIZONTAL
        topViewPager2.setPageTransformer(zoomOutPageTransformer)
        TabLayoutMediator(tabLayout, topViewPager2) { tab, position ->
            //Some implementation
        }.attach()
    }

}