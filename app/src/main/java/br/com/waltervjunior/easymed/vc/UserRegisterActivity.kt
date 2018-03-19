package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import br.com.waltervjunior.easymed.model.Doctor
import br.com.waltervjunior.easymed.model.Patient
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
                val paciente = Patient(
                        name = ui.nameEditText.text.toString(),
                        cellphone = ui.cellPhoneEditText.text.toString(),
                        dateOfBirth = ui.dateOfBirthEditText.text.toString(),
                        email = ui.emailEditText.text.toString(),
                        gender = ui.genderEditText.text.toString()
                )
                Log.d("Paciente", Gson().toJson(paciente))

                doAsync {
                    mAuth.createUserWithEmailAndPassword(paciente.email,  ui.passwordEditText.text.toString())
                            .addOnCompleteListener(this@UserRegisterActivity) { task ->
                                if (task.isSuccessful) {
                                    // Se o login for efetuado com sucesso
                                    val user = mAuth.currentUser
                                    Log.d("Usuário logado", Gson().toJson(user?.uid))

                                    //salvar os dados do usuário no firebase
                                    paciente.save(user!!.uid)

                                    //mandar o usuário pra tela principal
                                    startActivity(Intent(this@UserRegisterActivity, MainActivity::class.java))
                                } else {
                                    // Se o login falhar, validar as exception
                                    Log.d(TAG, "createUserWithEmail:failure", task.exception)
                                    toast("Authentication failed.")
                                }
                            }
                }
            }else{
                //medico
                val medico = Doctor(
                        cellphone = ui.cellPhoneEditText.text.toString(),
                        email = ui.emailEditText.text.toString(),
                        address = ui.addressEditText.text.toString(),
                        crm = ui.crmEditText.text.toString(),
                        phone = ui.telephoneEditText.text.toString(),
                        name = ui.nameEditText.text.toString(),
                        specialist = ui.specialistEditText.text.toString()
                )
                Log.d("Médico", Gson().toJson(medico))

                //region <! Criando a conta no firebase !>
                doAsync {
                    mAuth.createUserWithEmailAndPassword(medico.email,  ui.passwordEditText.text.toString())
                            .addOnCompleteListener(this@UserRegisterActivity) { task ->
                                if (task.isSuccessful) {
                                    // Se o login for efetuado com sucesso
                                    val user = mAuth.currentUser
                                    Log.d("Usuário logado", Gson().toJson(user?.uid))

                                    //salvar os dados do usuário no firebase
                                    medico.save(user!!.uid)

                                    //mandar o usuário pra tela principal
                                    startActivity(Intent(this@UserRegisterActivity, MainActivity::class.java))
                                } else {
                                    // Se o login falhar, validar as exception
                                    Log.d(TAG, "createUserWithEmail:failure", task.exception)
                                    toast("Authentication failed.")
                                }
                            }
                }
                //endregion
            }
        }
    }
}