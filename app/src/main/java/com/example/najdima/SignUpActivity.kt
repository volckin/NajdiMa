package com.example.najdima

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
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

        val drawableEmailEmpty : Drawable? = getDrawable(R.drawable.ic_email_empty)
        val drawableUsernameEmpty : Drawable? = getDrawable(R.drawable.ic_username_empty)
        val drawablePasswordEmpty : Drawable? = getDrawable(R.drawable.ic_password_empty)
        val drawableUsername : Drawable? = getDrawable(R.drawable.ic_username)
        val drawableEmail : Drawable? = getDrawable(R.drawable.ic_email)
        val drawablePassword : Drawable? = getDrawable(R.drawable.ic_password)


        /*
        //clickListener on btnSignIn
        btn_sign_up.setOnClickListener {
            if(et_username.text.isEmpty() && et_password.text.isEmpty()){
                //set tint & drawable of username & password to red
                SignInActivity.wrongUsernamePassword(et_username, et_password, drawableUsernameEmpty, drawablePasswordEmpty)
                makeToast(R.string.usernamePasswordEmpty)
            } else if (et_username.text.isEmpty()){
                //set tint & drawable of username to red
                wrongField(et_username, drawableUsernameEmpty)
                makeToast(R.string.usernameEmpty)
            } else if (et_password.text.isEmpty()){
                //set tint & drawable of username to red
                wrongField(et_password, drawablePasswordEmpty)
                makeToast(R.string.passwordEmpty)
            } else if (et_username.text.length < 5 && et_password.text.length < 5){
                wrongUsernamePassword(et_username, et_password, drawableUsernameEmpty, drawablePasswordEmpty)
                makeToast(R.string.usernamePasswordChars)
            } else if (et_username.text.length < 5){
                wrongField(et_username, drawableUsernameEmpty)
                makeToast(R.string.usernameChars)
            } else if (et_password.text.length < 5){
                wrongField(et_password, drawablePasswordEmpty)
                makeToast(R.string.passwordChars)
            } else {
                username = et_username.text.toString()
                println("USERNAME: " + username)
                password = et_password.text.toString()
                println("PASSWORD: " + password)

                mAuth?.signInWithEmailAndPassword(username.toString(), password.toString())
                    ?.addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("loginSuccess", "signInWithEmail:success")
                            val user = mAuth?.currentUser
                            logIn(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("loginFail", "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                            //updateUI(null)
                        }

                        // ...
                    }
            }

        }

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("SIGNUP_SUCCESS", "createUserWithEmail:success")
                    val user = mAuth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("SIGNUP_FAIL", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

                // ...
            }
        */
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth?.currentUser
        //updateUI(currentUser)
    }



    override fun onBackPressed() {
        startActivity(Intent(this, WelcomeActivity::class.java))
        this.finish()
    }
}
