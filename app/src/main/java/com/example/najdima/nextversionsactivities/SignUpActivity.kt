package com.example.najdima.nextversionsactivities

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.najdima.R
import com.google.firebase.auth.FirebaseAuth


class SignUpActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val etUsername : EditText = findViewById(R.id.et_username)
        val etEmail : EditText = findViewById(R.id.et_email)
        val etPassword : EditText = findViewById(R.id.et_password)
        val etRepeatPassword : EditText = findViewById(R.id.et_repeat_password)
        val etPhone : EditText = findViewById(R.id.et_phone)
        val chbConditions : CheckBox = findViewById(R.id.chb_conditions)
        val btnSignUp : Button = findViewById(R.id.btn_sign_up)
        var form : RegistrationForm?

        val drawableEmailEmpty : Drawable? = getDrawable(R.drawable.ic_email_empty)
        val drawableUsernameEmpty : Drawable? = getDrawable(R.drawable.ic_username_empty)
        val drawablePasswordEmpty : Drawable? = getDrawable(R.drawable.ic_password_empty)
        val drawableUsername : Drawable? = getDrawable(R.drawable.ic_username)
        val drawableEmail : Drawable? = getDrawable(R.drawable.ic_email)
        val drawablePassword : Drawable? = getDrawable(R.drawable.ic_password)


        //clickListener on btnSignIn
        btnSignUp.setOnClickListener {
            form = RegistrationForm(
                etUsername,
                etEmail,
                etPassword,
                etRepeatPassword,
                etPhone,
                chbConditions
            )
            RegistrationValidation.checkRegistrationFields(
                form,
                applicationContext
            )
        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        //val currentUser = mAuth?.currentUser
        //updateUI(currentUser)
    }



    override fun onBackPressed() {
        startActivity(Intent(this, WelcomeActivity::class.java))
        this.finish()
    }

}



