package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.setContentView


class LoginActivity : Activity() {
    lateinit var ui : LoginActivityUi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = LoginActivityUi()
        ui.setContentView(this)

        //region <! Evento dos botões !>
        ui.loginButton.onClick {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            //indeterminateProgressDialog("Logando", "Aguarde ...") {

            //}
        }
        ui.registerButton.onClick {
            val intent = Intent(this@LoginActivity, UserRegisterActivity::class.java)
            startActivity(intent)
        }
        //endregion

    }
}