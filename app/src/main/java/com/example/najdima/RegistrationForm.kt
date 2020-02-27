package com.example.najdima

import android.widget.CheckBox
import android.widget.EditText
import java.io.Serializable

class RegistrationForm(
    private val etUsername : EditText,
    private val etEmail : EditText,
    private val etPassword : EditText,
    private val etRepeatPassword : EditText,
    private val etPhone : EditText,
    private val chbConditions : CheckBox
) : Serializable{

    fun getET_username() : EditText {
        return this.etUsername
    }

    fun getET_email() : EditText {
        return this.etEmail
    }

    fun getET_password() : EditText {
        return this.etPassword
    }

    fun getET_repeat_password() : EditText {
        return this.etRepeatPassword
    }

    fun getET_phone() : EditText {
        return this.etPhone
    }

    fun getET_conditions() : CheckBox {
        return this.chbConditions
    }
}