package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import br.com.waltervjunior.easymed.model.Doctor
import br.com.waltervjunior.easymed.model.Pacient
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast


class UserRegisterActivity : Activity(){
    lateinit var ui : UserRegisterActivityUi
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = UserRegisterActivityUi()
        ui.setContentView(this)

        //instanciando o firebase
        mAuth = FirebaseAuth.getInstance()

        ui.backButton.onClick {
            onBackPressed()
        }

        ui.userToggleButton.setOnCheckedChangeListener({ _, state ->
            if (state){     //paciente
                ui.pacientContainer.visibility = View.VISIBLE
                ui.doctorContainer.visibility = View.GONE
            }else{          //medico
                ui.pacientContainer.visibility = View.GONE
                ui.doctorContainer.visibility = View.VISIBLE
            }
        })

        ui.createButton.onClick {
            //validar as paradas aqui
            if(ui.userToggleButton.isChecked){
                //paciente
                val paciente = Pacient(
                        cellphone = ui.cellPhoneEditText.text.toString(),
                        birthDate = ui.dateOfBirthEditText.text.toString(),
                        email = ui.emailEditText.text.toString(),
                        password = ui.passwordEditText.text.toString()
                )
                Log.d("Paciente", Gson().toJson(paciente))
                createUser(paciente.email, paciente.password)
            }else{
                //medico
                val medico = Doctor(
                        cellphone = ui.cellPhoneEditText.text.toString(),
                        email = ui.emailEditText.text.toString(),
                        password = ui.passwordEditText.text.toString(),
                        address = ui.addressEditText.text.toString(),
                        crm = ui.crmEditText.text.toString(),
                        telephone = ui.telephoneEditText.text.toString()
                )
                Log.d("Médico", Gson().toJson(medico))
            }
            //val intent = Intent(this@UserRegisterActivity, MainActivity::class.java)
            //startActivity(intent)
        }
    }

    private fun createUser(email : String, password : String){
        doAsync {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this@UserRegisterActivity) { task ->
                        if (task.isSuccessful) {
                            // Se o login for efetuado com sucesso
                            val user = mAuth.currentUser
                            Log.d("Usuário logado", Gson().toJson(user))

                            //mandar o usuário pra tela principal
                            //updateUI(user)
                        } else {
                            // Se o login falhar, validar as exception
                            Log.d(TAG, "createUserWithEmail:failure", task.exception)
                            toast("Authentication failed.")
                        }

                        // ...
                    }
        }
    }
}