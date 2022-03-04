package com.sbsj.memosbsj.activity

import android.app.Service
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager

import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.ViewSwitcher

import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sbsj.memosbsj.data.WrittenData
import com.sbsj.memosbsj.databinding.ActivityReadBinding
import com.sbsj.memosbsj.ext.makeDate
import com.sbsj.memosbsj.viewmodel.MainViewModel
import java.lang.Exception
import java.util.jar.Manifest


class ReadActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val MY_PERMISSION_ACCESS_ALL = 100

    lateinit var activityReadBinding: ActivityReadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionCheck()
        activityReadBinding = ActivityReadBinding.inflate(layoutInflater)
        activityReadBinding.viewModel = viewModel
        activityReadBinding.lifecycleOwner = this
        setContentView(activityReadBinding.root)

        val order = intent.getIntExtra("order", 0)
        viewModel.readData(order)
        viewModel.getReadData.observe(this, { activityReadBinding.readTitleTv.text = it.title })
        viewModel.getReadData.observe(this, { activityReadBinding.readContentTv.text = it.content })

        activityReadBinding.sendSms.setOnClickListener {
            sendSMS(
                "01067459098", "심성보 님의" + activityReadBinding.readTitleTv.text + "계좌번호는  " + activityReadBinding.readContentTv.text + " 입니다."
            )
        }
        activityReadBinding.readTitleTv.setOnClickListener {
            switchEditView(
                activityReadBinding.readTitleVs,
                activityReadBinding.readTitleTv,
                activityReadBinding.readTitleEdit
            )
        }
        activityReadBinding.readTitleEdit.setOnFocusChangeListener { v, b ->
            if (!b) {
                switchTextView(
                    activityReadBinding.readTitleVs,
                    activityReadBinding.readTitleEdit,
                    activityReadBinding.readTitleTv
                )
            }
        }

        activityReadBinding.readContentTv.setOnClickListener {
            switchEditView(
                activityReadBinding.readContentVs,
                activityReadBinding.readContentTv,
                activityReadBinding.readContentEt
            )
        }

        activityReadBinding.readContentEt.setOnFocusChangeListener { v, b ->
            if (!b) {
                switchTextView(
                    activityReadBinding.readContentVs,
                    activityReadBinding.readContentEt,
                    activityReadBinding.readContentTv
                )
            }
        }


        activityReadBinding.readCancelBtn.setOnClickListener {
            finish()
        }
        activityReadBinding.readDeleteBtn.setOnClickListener {

            AlertDialog.Builder(this)
                .setTitle("삭제 알림")
                .setMessage("정말로 삭제하시겠습니까?\n삭제를 할 경우에 기존데이터는 \n복구 할 수 없습니다.")
                .setPositiveButton(
                    "확인"
                ) { dialog, which ->
                    viewModel.delete(order)
                    finish()
                }
                .setNegativeButton(
                    "취소"
                ) { dialog, which -> }
                .create()
                .show()
        }
        activityReadBinding.readModifyBtn.setOnClickListener { v ->
            activityReadBinding.root.clearFocus()
            val imm: InputMethodManager =
                getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
            AlertDialog.Builder(this)
                .setTitle("수정 알림")
                .setMessage("정말로 수정하시겠습니까?\n수정을 할 경우에 기존데이터는 \n복구 할 수 없습니다.")
                .setPositiveButton(
                    "확인"
                ) { dialog, which ->
                    viewModel.update(
                        WrittenData(
                            order,
                            activityReadBinding.readTitleTv.text.toString(),
                            activityReadBinding.readContentTv.text.toString(),
                            makeDate()
                        )
                    )
                    finish()
                }
                .setNegativeButton(
                    "취소"
                ) { dialog, which -> }
                .create()
                .show()
        }

        activityReadBinding.readAllViewCl.setOnClickListener {
            activityReadBinding.root.clearFocus()
            val imm: InputMethodManager =
                getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }

    }

    private fun switchEditView(viewSwitcher: ViewSwitcher, textView: TextView, editText: EditText) {
        editText.setText(textView.text)
        viewSwitcher.showNext()  /*뷰 스위처의 하위뷰 호출*/
        editText.requestFocus()  /*포커스 얻음*/
        editText.setSelection(editText.length()); //커서를 끝에 위치!
        val imm: InputMethodManager =
            getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0) /*키패드 올림*/
    }

    private fun switchTextView(viewSwitcher: ViewSwitcher, editText: EditText, textView: TextView) {
        textView.text = editText.text
        viewSwitcher.showPrevious()
    }

    private fun sendSMS(phoneNumber: String, message: String) {


        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber,null,message,null,null)

            Toast.makeText(applicationContext, "전송 완료!", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "전송 오류!", Toast.LENGTH_LONG).show()
            Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_LONG).show() //오류 원인이 찍힌다.

            Log.e("", "sendSMS: "+e.printStackTrace() )
        }
    }


    private fun permissionCheck() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            var permissions = arrayOf(
                android.Manifest.permission.SEND_SMS,
            )
            ActivityCompat . requestPermissions (this, permissions, MY_PERMISSION_ACCESS_ALL)
        }
    }


}

