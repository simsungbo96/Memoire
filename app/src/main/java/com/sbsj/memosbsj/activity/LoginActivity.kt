package com.sbsj.memosbsj.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sbsj.memosbsj.R


class LoginActivity : AppCompatActivity() {
    lateinit var loginBtn : Button
    lateinit var id :EditText
    lateinit var password :EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }
    fun init(){
        loginBtn  = findViewById(R.id.login_btn_login)
        id = findViewById(R.id.login_et_id)
        password = findViewById(R.id.login_et_password)
        loginBtn.setOnClickListener {
            logIn(id.text.toString(),password.text.toString())
        }
    }

   /* 로그인 함수*/
    fun logIn(id :String , password :String){
        if(id=="1"){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        else{
             Toast.makeText(this,"아이디 비밀번호를 다시입력해주세요.",Toast.LENGTH_SHORT).show()
        }
    }

}


