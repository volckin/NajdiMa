package com.example.najdima

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.google.firebase.firestore.FirebaseFirestore


class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val db = FirebaseFirestore.getInstance()
        val btnSignIn : Button = findViewById(R.id.btn_sign_in)
        val et_username : EditText = findViewById(R.id.et_username)
        val et_password : EditText = findViewById(R.id.et_password)

        var username : String? = null
        var password : String? = null

        val drawableUsernameEmpty : Drawable? = getDrawable(R.drawable.ic_username_empty)
        val drawablePasswordEmpty : Drawable? = getDrawable(R.drawable.ic_password_empty)
        val drawableUsername : Drawable? = getDrawable(R.drawable.ic_username)
        val drawablePassword : Drawable? = getDrawable(R.drawable.ic_password)

        //set et_username to original state
        et_username.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (et_username.backgroundTintList == ColorStateList.valueOf(
                        ContextCompat.getColor(applicationContext,
                            R.color.gradientRStartColor))){
                    et_username.setBackgroundTintList( ColorStateList.valueOf(
                        ContextCompat.getColor(applicationContext,
                            R.color.gradientCenterColor)))
                    et_username.setCompoundDrawablesWithIntrinsicBounds(drawableUsername, null, null, null)
                }
            }
        })

        //set et_password to original state
        et_password.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (et_password.backgroundTintList == ColorStateList.valueOf(
                        ContextCompat.getColor(applicationContext,
                            R.color.gradientRStartColor))){
                    et_password.setBackgroundTintList( ColorStateList.valueOf(
                        ContextCompat.getColor(applicationContext,
                            R.color.gradientCenterColor)))
                    et_password.setCompoundDrawablesWithIntrinsicBounds(drawablePassword, null, null, null)
                }
            }
        })

        //clickListener on btnSignIn
        btnSignIn.setOnClickListener {
            if(et_username.text.isEmpty() && et_password.text.isEmpty()){
                //set tint & drawable of username & password to red
                wrongUsernamePassword(et_username, et_password, drawableUsernameEmpty, drawablePasswordEmpty)
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

                db.collection("users").document()
                    .get()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            for (document in task.result!!) {
                                //Log.d(FragmentActivity.TAG, document.id + " => " + document.data)
                                if (document.getString("username") != username){
                                    wrongField(et_username, drawableUsernameEmpty)
                                    makeToast(R.string.wrongUsername)
                                } else if (document.getString("password") != password) {
                                    wrongField(et_password, drawablePasswordEmpty)
                                    makeToast(R.string.wrongPassword)
                                }
                            }
                        } else {
                            //Log.w(FragmentActivity.TAG, "Error getting documents.", task.exception)
                        }
                    }
            }

        }

    }

    private fun makeToast(txt: Int) {
        Toast.makeText(this, txt, Toast.LENGTH_LONG).show()
    }


    //set tint & drawable of username/password to red
    private fun wrongField(txt: EditText, img: Drawable?) {
        txt.setBackgroundTintList( ColorStateList.valueOf(
            ContextCompat.getColor(applicationContext,
                R.color.gradientRStartColor)))
        txt.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null)
    }

    //set tint & drawable of username & password to red
    private fun wrongUsernamePassword(
        etUsername: EditText,
        etPassword: EditText,
        drawableUsernameEmpty: Drawable?,
        drawablePasswordEmpty: Drawable?
    ) {
        etUsername.setBackgroundTintList( ColorStateList.valueOf(
            ContextCompat.getColor(applicationContext,
                R.color.gradientRStartColor)))
        etUsername.setCompoundDrawablesWithIntrinsicBounds(drawableUsernameEmpty, null, null, null)
        etPassword.setBackgroundTintList( ColorStateList.valueOf(
            ContextCompat.getColor(applicationContext,
                R.color.gradientRStartColor)))
        etPassword.setCompoundDrawablesWithIntrinsicBounds(drawablePasswordEmpty, null, null, null)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, WelcomeActivity::class.java))
        this.finish()
    }
}
