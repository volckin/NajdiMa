package com.example.najdima.nextversionsactivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.najdima.R


class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val btnSignIn : Button = findViewById(R.id.btn_w_sign_in)
        val btnSignUp : Button = findViewById(R.id.btn_w_sign_up)

        btnSignIn.setOnClickListener {
            val i = Intent(this, SignInActivity::class.java)
            startActivity(i)
            finish()
        }

        btnSignUp.setOnClickListener {
            val i = Intent(this, SignUpActivity::class.java)
            startActivity(i)
            finish()
        }

    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        // Display a message on alert dialog
        builder.setMessage(R.string.exitDialogTitle)
        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton(R.string.positive){ dialog, which ->
            this.finish()
            System.exit(0)
        }
        // Display a negative button on alert dialog
        builder.setNegativeButton(R.string.negative){ dialog, which ->
        }
        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()
        // Display the alert dialog on app interface
        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(applicationContext,
            R.color.gradientRStartColor
        ))
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(applicationContext,
            R.color.gradientCenterColor
        ))
    }
}
