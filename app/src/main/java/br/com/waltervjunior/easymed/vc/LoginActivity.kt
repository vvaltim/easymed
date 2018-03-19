package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.setContentView


class LoginActivity : Activity() {
    lateinit var ui : LoginActivityUi
    lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = LoginActivityUi()
        ui.setContentView(this)

        //instanciando o firebase
        mAuth = FirebaseAuth.getInstance()

        //region <! Ao clicar em login !>
        ui.loginButton.onClick {
            doAsync {
                mAuth.signInWithEmailAndPassword(ui.emailEditText.text.toString(), ui.passwordEditText.text.toString())
                        .addOnCompleteListener(this@LoginActivity, { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                val user = mAuth.currentUser
                                Log.d("signInWithEmail:success", Gson().toJson(user))
                                //se o login for efetuado com sucesso
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                                //updateUI(user)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.exception)
                                Toast.makeText(this@LoginActivity, "Falha ao efetuar login.",
                                        Toast.LENGTH_LONG).show()
                            }

                        })
            }
        }
        //endregion

        //region <! Ao clicar em registrar !>
        ui.registerButton.onClick {
            val intent = Intent(this@LoginActivity, UserRegisterActivity::class.java)
            startActivity(intent)
        }
        //endregion

    }
}