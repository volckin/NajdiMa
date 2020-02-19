package com.example.najdima

import android.widget.CheckBox
import android.widget.EditText
import java.io.Serializable

class RegistrationForm(
    private val et_username : EditText,
    private val et_email : EditText,
    private val et_password : EditText,
    private val et_repeat_password : EditText,
    private val et_phone : EditText,
    private val chb_conditions : CheckBox
) : Serializable{

    fun getET_username() : EditText {
        return this.et_username
    }

    fun getET_email() : EditText {
        return this.et_email
    }

    fun getET_password() : EditText {
        return this.et_password
    }

    fun getET_repeat_password() : EditText {
        return this.et_repeat_password
    }

    fun getET_phone() : EditText {
        return this.et_phone
    }

    fun getET_conditions() : CheckBox {
        return this.chb_conditions
    }
}