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
import com.sbsj.memosbsj.R


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginBtn : Button = findViewById(R.id.login_btn_login)
        var id :EditText= findViewById(R.id.login_et_id)
        var password :EditText= findViewById(R.id.login_et_password)
        loginBtn.setOnClickListener {
            login(id.text.toString(),password.text.toString())
        }
    }


    fun init(){

    }
    fun login(id :String , password :String){

        if(id=="1" && password=="1")
        {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }
        else{
             Toast.makeText(this,"아이디 비밀번호를 다시입력해주세요.",Toast.LENGTH_SHORT).show()
        }


    }
}


