package com.example.loginmaterialg1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val  sharedpreferences = getSharedPreferences("SP_INFO", MODE_PRIVATE);
        txt_name_user.text = sharedpreferences.getString("user_name", "")
    }
}