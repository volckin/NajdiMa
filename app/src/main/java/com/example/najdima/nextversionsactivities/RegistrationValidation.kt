package com.example.najdima.nextversionsactivities

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.Patterns
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import com.example.najdima.R

object RegistrationValidation {

    fun checkRegistrationFields(
        form: RegistrationForm?,
        applicationContext: Context
    ) {
        val etUsername: EditText? = form?.getET_username()
        val etEmail: EditText? = form?.getET_email()
        val etPassword: EditText? = form?.getET_password()
        val etRepeatPassword: EditText? = form?.getET_repeat_password()
        val etPhone: EditText? = form?.getET_phone()
        val chbConditions: CheckBox? = form?.getET_conditions()

        val username: String = form?.getET_username()?.text.toString()
        val email: String = form?.getET_email()?.text.toString()
        val password: String = form?.getET_password()?.text.toString()
        val repeatPassword: String = form?.getET_repeat_password()?.text.toString()
        val phone: String = form?.getET_phone()?.text.toString()
        val conditions: Boolean? = form?.getET_conditions()?.isChecked


        if (username.length < 5)
            emptyField(
                etUsername,
                Field.Username,
                applicationContext
            )
        else
            filledField(
                etUsername,
                Field.Username,
                applicationContext
            )

        if (!isEmailValid(
                email
            )
        )
            emptyField(
                etEmail,
                Field.Email,
                applicationContext
            )
        else
            filledField(
                etEmail,
                Field.Email,
                applicationContext
            )

        if (password.length < 5) {
            emptyField(
                etPassword,
                Field.Password,
                applicationContext
            )
            emptyField(
                etRepeatPassword,
                Field.Password,
                applicationContext
            )
        } else if (password != repeatPassword) {
            emptyField(
                etPassword,
                Field.Password,
                applicationContext
            )
            emptyField(
                etRepeatPassword,
                Field.Password,
                applicationContext
            )
            makeToast(
                applicationContext,
                R.string.noEqualsPasswords
            )
        } else {
            filledField(
                etPassword,
                Field.Password,
                applicationContext
            )
            filledField(
                etRepeatPassword,
                Field.Password,
                applicationContext
            )
        }

        if (!phone.isEmpty() && !validPhoneNumber(
                phone
            )
        )
            emptyField(
                etPhone,
                Field.PhoneNumber,
                applicationContext
            )
        else
            filledField(
                etPhone,
                Field.PhoneNumber,
                applicationContext
            )


    }

    private fun filledField(editText: EditText?, type: Field, applicationContext: Context) {

        val drawableUsername : Drawable? = getDrawable(applicationContext,
            R.drawable.ic_username
        )
        val drawableEmail : Drawable? = getDrawable(applicationContext,
            R.drawable.ic_email
        )
        val drawablePassword : Drawable? = getDrawable(applicationContext,
            R.drawable.ic_password
        )
        val drawableRequired : Drawable? = getDrawable(applicationContext,
            R.drawable.ic_required
        )

        editText?.setBackgroundTintList( ColorStateList.valueOf(
            ContextCompat.getColor(applicationContext,
                R.color.gradientCenterColor
            )))

        when(type){
            Field.Username -> editText?.setCompoundDrawablesWithIntrinsicBounds(drawableUsername, null, drawableRequired, null)
            Field.Email -> editText?.setCompoundDrawablesWithIntrinsicBounds(drawableEmail, null, drawableRequired, null)
            Field.Password -> {
                editText?.setCompoundDrawablesWithIntrinsicBounds(drawablePassword, null, drawableRequired, null)
                editText?.text?.clear()
            }
        }
    }

    private fun makeToast(ctx: Context, txt: Int) {
        Toast.makeText(ctx, txt, Toast.LENGTH_LONG).show()
    }

    private fun emptyField(
        editText: EditText?,
        type: Field,
        applicationContext: Context
    ) {
        val drawableEmailEmpty : Drawable? = getDrawable(applicationContext,
            R.drawable.ic_email_empty
        )
        val drawableUsernameEmpty : Drawable? = getDrawable(applicationContext,
            R.drawable.ic_username_empty
        )
        val drawablePasswordEmpty : Drawable? = getDrawable(applicationContext,
            R.drawable.ic_password_empty
        )
        val drawablePhoneEmpty : Drawable? = getDrawable(applicationContext,
            R.drawable.ic_phone_number_empty
        )
        val drawableRequiredEmpty : Drawable? = getDrawable(applicationContext,
            R.drawable.ic_required_empty
        )

        editText?.setBackgroundTintList( ColorStateList.valueOf(
            ContextCompat.getColor(applicationContext,
                R.color.gradientRStartColor
            )))

        when(type){
            Field.Username -> editText?.setCompoundDrawablesWithIntrinsicBounds(drawableUsernameEmpty, null, drawableRequiredEmpty, null)
            Field.Email -> {
                editText?.setCompoundDrawablesWithIntrinsicBounds(drawableEmailEmpty, null, drawableRequiredEmpty, null)
                editText?.setTextColor(applicationContext.getResources().getColor(R.color.et_color))
            }
            Field.Password -> {
                editText?.setCompoundDrawablesWithIntrinsicBounds(drawablePasswordEmpty, null, drawableRequiredEmpty, null)
                editText?.text?.clear()
            }
            Field.PhoneNumber -> editText?.setCompoundDrawablesWithIntrinsicBounds(drawablePhoneEmpty, null, null, null)
        }
        makeToast(
            applicationContext,
            R.string.emptyFields
        )


    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validPhoneNumber(number: String?): Boolean {
        return Patterns.PHONE.matcher(number.toString()).matches()
    }

}