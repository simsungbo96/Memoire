package com.sbsj.memosbsj.ext

import android.app.Activity
import java.text.SimpleDateFormat
import java.util.*


fun Activity.makeDate(): String {
    var nowDate = System.currentTimeMillis()
    val convertNowDate = Date(nowDate)
    val printDate = SimpleDateFormat("yy년 MM월 dd일")
    return printDate.format(convertNowDate)
}



