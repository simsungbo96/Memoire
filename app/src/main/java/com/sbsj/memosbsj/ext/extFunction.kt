package com.sbsj.memosbsj.ext

import android.app.Activity
import android.app.Service
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import java.text.SimpleDateFormat
import java.util.*


fun makeDate(): String {
    var nowDate = System.currentTimeMillis()
    val convertNowDate = Date(nowDate)
    val printDate = SimpleDateFormat("yy년 MM월 dd일")
    return printDate.format(convertNowDate)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
