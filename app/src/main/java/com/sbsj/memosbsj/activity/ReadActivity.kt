package com.sbsj.memosbsj.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.sbsj.memosbsj.data.WrittenData
import com.sbsj.memosbsj.databinding.ActivityReadBinding
import com.sbsj.memosbsj.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReadActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    lateinit var activityReadBinding: ActivityReadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityReadBinding = ActivityReadBinding.inflate(layoutInflater)
        activityReadBinding.viewModel = viewModel
        activityReadBinding.lifecycleOwner =this
        setContentView(activityReadBinding.root)


        Log.e("read", "viewModel.Repository : "+ viewModel.repository )
        viewModel.readData(10)
        viewModel.getReadData.observe(this, Observer { activityReadBinding.readContentTv.text = it.title })
    }


}