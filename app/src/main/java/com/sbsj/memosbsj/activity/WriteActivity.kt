package com.sbsj.memosbsj.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import com.sbsj.memosbsj.databinding.ActivityWriteBinding
import com.sbsj.memosbsj.ext.makeDate
import com.sbsj.memosbsj.fragment.EditFragment
import com.sbsj.memosbsj.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*




class WriteActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var activityWriteBinding : ActivityWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityWriteBinding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(activityWriteBinding.root)

         activityWriteBinding.writeWriteBtn.setOnClickListener {
            backMainActivity()
        }
        activityWriteBinding.writeCancelBtn.setOnClickListener {
            finish()
        }

    }
    fun backMainActivity(){
        viewModel.insert(WrittenData(0, activityWriteBinding.writeTitleEt.text.toString(),activityWriteBinding.writeContentEt.text.toString(),makeDate()))
        finish()
    }

}