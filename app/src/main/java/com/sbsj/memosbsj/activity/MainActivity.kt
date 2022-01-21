package com.sbsj.memosbsj.activity




import HomeFragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.google.android.material.bottomnavigation.BottomNavigationView

import com.sbsj.memosbsj.R
import com.sbsj.memosbsj.fragment.EditFragment

import com.sbsj.memosbsj.fragment.SetFragment
import com.sbsj.memosbsj.fragment.ShareFragment


class MainActivity : AppCompatActivity() {


    lateinit var bottomNavigationView : BottomNavigationView
    lateinit var menu :Menu
    private val fragmentHome by lazy { HomeFragment() }
    private val fragmentEdit by lazy { EditFragment() }
    private val fragmentShare by lazy { ShareFragment() }
    private val fragmentSet by lazy { SetFragment() }

    private var backKeyPressedTime : Long =0;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavViewCreate()

    }

    override fun onRestart() {
        super.onRestart()

    }
    override fun onBackPressed() {
        exitApp()
    }









    /*바텀 네비게이션뷰 생성 */
    fun bottomNavViewCreate()
    {
        bottomNavigationView=findViewById(R.id.mainAct_bottomNaviView);
        menu=bottomNavigationView.menu;
        bottomNavigationView.run{
            setOnItemSelectedListener {item->
                when(item.itemId){
                    R.id.action_home-> { changeFragment(fragmentHome) }
                    R.id.action_edit-> { changeFragment(fragmentEdit) }
                    R.id.action_share-> { changeFragment(fragmentShare) }
                    R.id.action_set-> { changeFragment(fragmentSet) }
                }
                true
            }
            selectedItemId = R.id.action_home
        }
    }
    /*바텀 네비게이션뷰 화면전환하는 함수 */
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager .beginTransaction() .replace(R.id.main_frameLayout, fragment) .commit()
    }

    /*메인액티비티에서 뒤로가기 두번하면 앱을 종료하는 함수 */
    private fun exitApp(){
        if (System.currentTimeMillis() > backKeyPressedTime + 2500){
            backKeyPressedTime =System.currentTimeMillis()
            Toast.makeText(this, "뒤로 가기 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_LONG).show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2500){
            finishAffinity();
        }
    }

}