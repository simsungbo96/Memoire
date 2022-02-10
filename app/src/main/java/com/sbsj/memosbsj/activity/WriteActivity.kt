package com.sbsj.memosbsj.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sbsj.memosbsj.data.WrittenData
import com.sbsj.memosbsj.databinding.ActivityWriteBinding
import com.sbsj.memosbsj.ext.makeDate
import com.sbsj.memosbsj.viewmodel.MainViewModel



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