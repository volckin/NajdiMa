package com.example.najdima

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val i = Intent(this, WelcomeActivity::class.java)
        Handler().postDelayed({
            startActivity(i)
            finish()
        }, 1500)

    }

    override fun onBackPressed() {
            println("Attempt to close app!!!")
    }
}
