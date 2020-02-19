package com.example.najdima

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class SignUpActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val et_username : EditText = findViewById(R.id.et_username)
        val et_email : EditText = findViewById(R.id.et_email)
        val et_password : EditText = findViewById(R.id.et_password)
        val et_repeat_password : EditText = findViewById(R.id.et_repeat_password)
        val et_phone : EditText = findViewById(R.id.et_phone)
        val chb_conditions : CheckBox = findViewById(R.id.chb_conditions)
        val btn_sign_up : Button = findViewById(R.id.btn_sign_up)
        var form : RegistrationForm? = null

        val drawableEmailEmpty : Drawable? = getDrawable(R.drawable.ic_email_empty)
        val drawableUsernameEmpty : Drawable? = getDrawable(R.drawable.ic_username_empty)
        val drawablePasswordEmpty : Drawable? = getDrawable(R.drawable.ic_password_empty)
        val drawableUsername : Drawable? = getDrawable(R.drawable.ic_username)
        val drawableEmail : Drawable? = getDrawable(R.drawable.ic_email)
        val drawablePassword : Drawable? = getDrawable(R.drawable.ic_password)


        //clickListener on btnSignIn
        btn_sign_up.setOnClickListener {
            form = RegistrationForm(et_username, et_email, et_password, et_repeat_password, et_phone, chb_conditions)
            RegistrationValidation.checkRegistrationFields(form, applicationContext)
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



