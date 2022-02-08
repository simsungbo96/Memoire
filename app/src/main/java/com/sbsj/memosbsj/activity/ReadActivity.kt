package com.sbsj.memosbsj.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.ViewSwitcher

import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.sbsj.memosbsj.data.WrittenData
import com.sbsj.memosbsj.databinding.ActivityReadBinding
import com.sbsj.memosbsj.ext.makeDate
import com.sbsj.memosbsj.viewmodel.MainViewModel


class ReadActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    lateinit var activityReadBinding: ActivityReadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityReadBinding = ActivityReadBinding.inflate(layoutInflater)
        activityReadBinding.viewModel = viewModel
        activityReadBinding.lifecycleOwner =this
        setContentView(activityReadBinding.root)

        val order = intent.getIntExtra("order",0)
        viewModel.readData(order)
        viewModel.getReadData.observe(this,{activityReadBinding.readTitleTv.text = it.title })
        viewModel.getReadData.observe(this,{activityReadBinding.readContentTv.text = it.content})

        activityReadBinding.readTitleVs.setOnClickListener {
            switchEditView(activityReadBinding.readTitleVs , activityReadBinding.readTitleTv , activityReadBinding.readTitleEdit)
                      activityReadBinding.readTitleVs.onFocusChangeListener =
                View.OnFocusChangeListener { p0, hasFocus ->
                    if(p0.isFocusable){
                        switchEditView(activityReadBinding.readTitleVs , activityReadBinding.readTitleTv , activityReadBinding.readTitleEdit)
                        Log.e("setOnFocusChangeListener", "focus ", )
                    } else{
                        switchTextView(activityReadBinding.readTitleVs ,activityReadBinding.readTitleEdit  , activityReadBinding.readTitleTv)
                        Log.e("setOnFocusChangeListener", "nofocus ", )
                    }
                }
        }


        activityReadBinding.readContentVs.setOnClickListener {
            switchEditView(activityReadBinding.readContentVs , activityReadBinding.readContentTv , activityReadBinding.readContentEt)
            activityReadBinding.readContentVs.onFocusChangeListener =
                View.OnFocusChangeListener { p0, hasFocus ->
                    if(p0.isFocusable){
                        switchEditView(activityReadBinding.readContentVs , activityReadBinding.readContentTv , activityReadBinding.readContentEt)
                        Log.e("setOnFocusChangeListener", "focus ", )
                    } else{
                        switchTextView(activityReadBinding.readContentVs ,activityReadBinding.readContentEt  , activityReadBinding.readContentTv)
                        Log.e("setOnFocusChangeListener", "nofocus ", )
                    }
                }
        }


        activityReadBinding.readCancelBtn.setOnClickListener {
            finish()
        }
        activityReadBinding.readDeleteBtn.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("삭제 알림")
                .setMessage("정말로 삭제하시겠습니까?\n삭제를 하게되면 기존데이터는 \n복구 할 수 없습니다.")
                .setPositiveButton("확인"
                ) { dialog, which ->
                    viewModel.delete(order)
                    finish()
                }
                .setNegativeButton("취소"
                ) { dialog, which -> }
                .create()
                .show()
        }
        activityReadBinding.readModifyBtn.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("수정 알림")
                .setMessage("정말로 수정하시겠습니까?\n수정을 하게되면 기존데이터는 \n복구 할 수 없습니다.")
                .setPositiveButton("확인"
                ) { dialog, which ->
                    viewModel.update(WrittenData(order,activityReadBinding.readTitleTv.text.toString(),activityReadBinding.readContentTv.text.toString(),this.makeDate()))
                    finish()
                }
                .setNegativeButton("취소"
                ) { dialog, which -> }
                .create()
                .show()
        }


    }

    private fun switchEditView(viewSwitcher : ViewSwitcher , textView : TextView , editText: EditText){
        editText.setText(textView.text)
        viewSwitcher.showNext()
    }
    private fun switchTextView(viewSwitcher : ViewSwitcher, editText: EditText ,textView : TextView){
        textView.text = editText.text
        viewSwitcher.showNext()

    }

}

