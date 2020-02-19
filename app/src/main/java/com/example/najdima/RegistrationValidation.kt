package com.example.najdima

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.Patterns
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat

object RegistrationValidation {

    fun checkRegistrationFields(
        form: RegistrationForm?,
        applicationContext: Context
    ) {
        val et_username: EditText? = form?.getET_username()
        val et_email: EditText? = form?.getET_email()
        val et_password: EditText? = form?.getET_password()
        val et_repeat_password: EditText? = form?.getET_repeat_password()
        val et_phone: EditText? = form?.getET_phone()
        val chb_conditions: CheckBox? = form?.getET_conditions()

        val username: String = form?.getET_username()?.text.toString()
        val email: String = form?.getET_email()?.text.toString()
        val password: String = form?.getET_password()?.text.toString()
        val repeat_password: String = form?.getET_repeat_password()?.text.toString()
        val phone: String = form?.getET_phone()?.text.toString()
        val conditions: Boolean? = form?.getET_conditions()?.isChecked


        if (username.length < 5)
            emptyField(et_username, Field.Username, applicationContext)

        if (!isEmailValid(email))
            emptyField(et_email, Field.Email, applicationContext)

        if (password.length < 5) {
            emptyField(et_password, Field.Password, applicationContext)
            emptyField(et_repeat_password, Field.Password, applicationContext)
        } else if (password != repeat_password) {
            emptyField(et_password, Field.Password, applicationContext)
            emptyField(et_repeat_password, Field.Password, applicationContext)
            makeToast(applicationContext, R.string.noEqualsPasswords)
        }

        if (!phone.isEmpty() && !validPhoneNumber(phone))
            emptyField(et_phone, Field.PhoneNumber, applicationContext)


    }

    private fun makeToast(ctx: Context, txt: Int) {
        Toast.makeText(ctx, txt, Toast.LENGTH_LONG).show()
    }

    private fun emptyField(
        editText: EditText?,
        type: Field,
        applicationContext: Context
    ) {
        val drawableEmailEmpty : Drawable? = getDrawable(applicationContext, R.drawable.ic_email_empty)
        val drawableUsernameEmpty : Drawable? = getDrawable(applicationContext, R.drawable.ic_username_empty)
        val drawablePasswordEmpty : Drawable? = getDrawable(applicationContext, R.drawable.ic_password_empty)
        val drawablePhoneEmpty : Drawable? = getDrawable(applicationContext, R.drawable.ic_phone_number_empty)
        val drawableRequiredEmpty : Drawable? = getDrawable(applicationContext, R.drawable.ic_required_empty)

        val drawableUsername : Drawable? = getDrawable(applicationContext, R.drawable.ic_username)
        val drawableEmail : Drawable? = getDrawable(applicationContext, R.drawable.ic_email)
        val drawablePassword : Drawable? = getDrawable(applicationContext, R.drawable.ic_password)
        val drawableRequired : Drawable? = getDrawable(applicationContext, R.drawable.ic_required)

        editText?.setBackgroundTintList( ColorStateList.valueOf(
            ContextCompat.getColor(applicationContext,
                R.color.gradientCenterColor)))

        when(type){
            Field.Username -> editText?.setCompoundDrawablesWithIntrinsicBounds(drawableUsernameEmpty, null, drawableRequiredEmpty, null)
            Field.Email -> editText?.setCompoundDrawablesWithIntrinsicBounds(drawableEmailEmpty, null, drawableRequiredEmpty, null)
            Field.Password -> {
                editText?.setCompoundDrawablesWithIntrinsicBounds(drawablePasswordEmpty, null, drawableRequiredEmpty, null)
                editText?.text?.clear()
            }
            Field.PhoneNumber -> editText?.setCompoundDrawablesWithIntrinsicBounds(drawablePhoneEmpty, null, null, null)
        }


    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validPhoneNumber(number: String?): Boolean {
        return Patterns.PHONE.matcher(number).matches()
    }

}