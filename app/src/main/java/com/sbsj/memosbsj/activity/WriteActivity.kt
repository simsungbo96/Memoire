package com.sbsj.memosbsj.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sbsj.memosbsj.R
import com.sbsj.memosbsj.adapter.WriteAdapter
import com.sbsj.memosbsj.data.WrittenData
import com.sbsj.memosbsj.fragment.EditFragment

lateinit var writeButton: Button
lateinit var writtenTitle : EditText



class WriteActivity : AppCompatActivity() {
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
        intent.putExtra("title",writtenTitle.text.toString());
        setResult(Activity.RESULT_OK,intent)
        finish()
    }



}