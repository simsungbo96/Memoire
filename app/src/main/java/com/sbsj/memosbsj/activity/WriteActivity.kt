package com.sbsj.memosbsj.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sbsj.memosbsj.R
import com.sbsj.memosbsj.adapter.WriteAdapter
import com.sbsj.memosbsj.data.WrittenData
import com.sbsj.memosbsj.fragment.EditFragment
import com.sbsj.memosbsj.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

lateinit var writeButton: Button
lateinit var writtenTitle : EditText



class WriteActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        writtenTitle = findViewById(R.id.write_title_et)
        writeButton = findViewById(R.id.write_write_btn)

        writeButton.setOnClickListener {
            backMainActivity();

        }

    }
    fun backMainActivity(){
        var nowDate = System.currentTimeMillis()
        val convertNowDate = Date(nowDate)
        val printDate = SimpleDateFormat("yy년 MM월 dd일")
        viewModel.insert(WrittenData(0, writtenTitle.text.toString(),printDate.format(convertNowDate)))
        finish()
    }



}