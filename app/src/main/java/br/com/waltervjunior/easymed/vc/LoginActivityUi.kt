package br.com.waltervjunior.easymed.vc

import android.support.v7.widget.CardView
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import br.com.waltervjunior.easymed.R
import org.jetbrains.anko.*

class LoginActivityUi : AnkoComponent<LoginActivity>{
    lateinit var imageLogo : ImageView
    lateinit var emailEditText : EditText
    lateinit var passwordEditText : EditText
    lateinit var loginButton : Button
    lateinit var registerButton : Button
    lateinit var forgetPasswordButton : Button
    lateinit var formLoginCard : CardView

    override fun createView(ui: AnkoContext<LoginActivity>) = with(ui) {
        relativeLayout {
            lparams {
                gravity = Gravity.CENTER
            }
            imageLogo = imageView {
                id = View.generateViewId()
                imageResource = R.drawable.icon
            }.lparams(dip(150), dip(150)){
                centerHorizontally()
                elevation = 16f
                bottomMargin = dip(15)
            }

            emailEditText = editText {
                id = View.generateViewId()
                hint = context.getString(R.string.email)
            }.lparams (matchParent){
                below(imageLogo)
                marginStart = dip(10)
                marginEnd = dip(10)
            }
            passwordEditText = editText {
                id = View.generateViewId()
                hint = context.getString(R.string.password)
            }.lparams (matchParent){
                below(emailEditText)
                marginStart = dip(10)
                marginEnd = dip(10)
            }
            loginButton = themedButton(R.style.buttonPrimary) {
                id = View.generateViewId()
                text = context.getString(R.string.login)
            }.lparams {
                below(passwordEditText)
                centerHorizontally()
            }
            registerButton = themedButton(R.style.buttonPrimary) {
                id = View.generateViewId()
                text = context.getString(R.string.register_account)
            }.lparams {
                below(loginButton)
                centerHorizontally()
            }
            forgetPasswordButton = themedButton(R.style.buttonPrimary) {
                id = View.generateViewId()
                text = context.getString(R.string.forget_password)
            }.lparams {
                below(registerButton)
                centerHorizontally()
            }
        }
    }
}