package br.com.waltervjunior.easymed.vc

import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import br.com.waltervjunior.easymed.R
import br.com.waltervjunior.easymed.extension.generateViewId
import br.com.waltervjunior.easymed.extension.setSelectableItemBackground
import org.jetbrains.anko.*

class LoginActivityUi : AnkoComponent<LoginActivity>{
    lateinit var imageLogo : ImageView
    lateinit var emailEditText : EditText
    lateinit var passwordEditText : EditText
    lateinit var loginButton : Button
    lateinit var registerButton : Button
    lateinit var forgetPasswordTextView : TextView

    override fun createView(ui: AnkoContext<LoginActivity>) = with(ui) {
        relativeLayout {
            padding = dip(20)
            lparams {
                gravity = Gravity.CENTER
            }
            imageLogo = imageView {
                generateViewId()
                imageResource = R.drawable.icon
            }.lparams(dip(150), dip(150)){
                centerHorizontally()
                bottomMargin = dip(15)
            }

            emailEditText = editText {
                generateViewId()
                hint = context.getString(R.string.email)
                inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_URI
                maxLines = 1
                setHorizontallyScrolling(true)
                textSize = 16f
            }.lparams (matchParent){
                below(imageLogo)
                marginStart = dip(10)
                marginEnd = dip(10)
            }
            passwordEditText = editText {
                generateViewId()
                hint = context.getString(R.string.password)
                textSize = 16f
                inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                maxLines = 1
                setHorizontallyScrolling(true)
            }.lparams (matchParent){
                below(emailEditText)
                marginStart = dip(10)
                marginEnd = dip(10)
            }

            loginButton = button {
                generateViewId()
                text = context.getString(R.string.login)
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
                textColor = Color.WHITE
            }.lparams(matchParent){
                below(passwordEditText)
                centerHorizontally()
            }
            registerButton = button {
                generateViewId()
                text = context.getString(R.string.register_account)
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
                textColor = Color.WHITE
            }.lparams(matchParent){
                below(loginButton)
                centerHorizontally()
            }
            forgetPasswordTextView = textView(R.string.forget_password) {
                generateViewId()
                setSelectableItemBackground()
                topPadding = dip(10)
                bottomPadding = dip(10)
                gravity = Gravity.CENTER
                isClickable = true
            }.lparams(width = matchParent) {
                below(registerButton)
                topMargin = dip(10)
            }
        }
    }
}