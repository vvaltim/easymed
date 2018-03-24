package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import br.com.waltervjunior.easymed.extension.longSnackbar
import br.com.waltervjunior.easymed.service.PreferenceService
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import org.jetbrains.anko.contentView
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onEditorAction
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

        //region <! Ao clicar em registrar !>
        ui.registerButton.onClick {
            val intent = Intent(this@LoginActivity, UserRegisterActivity::class.java)
            startActivity(intent)
        }
        //endregion

        //region <! Quando pressionar Enter !>
        ui.passwordEditText.onEditorAction { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                //hideSoftKeyboard()
                ui.loginButton.callOnClick()
            }
        }
        //endregion

        //region <! Ao clicar em login !>
        ui.loginButton.onClick {
            if (ui.emailEditText.text.isEmpty() || ui.passwordEditText.text.isEmpty()){
                contentView?.longSnackbar("É necessário informar usuário e senha")
            } else {
                indeterminateProgressDialog("Autenticando...") {
                    setCancelable(false)
                    setOnShowListener {
                        doAsync {
                            mAuth.signInWithEmailAndPassword(ui.emailEditText.text.toString(), ui.passwordEditText.text.toString())
                                    .addOnCompleteListener(this@LoginActivity, { task ->
                                        dismiss()
                                        if (task.isSuccessful) {
                                            val user = mAuth.currentUser
                                            if(user != null){
                                                // Login efetuado com sucesso
                                                Log.d("signInWithEmail:success", Gson().toJson(user))
                                                //salvar o Uid da pessoa logada no PreferencesService
                                                PreferenceService.set("USER_ID", user.uid)
                                                //se o login for efetuado com sucesso
                                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                                startActivity(intent)
                                            }
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                                            contentView?.longSnackbar("Usuário ou senha incorretos")
                                        }
                                    })
                        }
                    }
                }
            }
        }
        //endregion
    }
}