package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import br.com.waltervjunior.easymed.extension.longSnackbar
import br.com.waltervjunior.easymed.model.Doctor
import br.com.waltervjunior.easymed.model.Patient
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import org.jetbrains.anko.contentView
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

        //region <! Botão para alterar o tipo de conta criada !>
        ui.userToggleButton.setOnCheckedChangeListener({ _, state ->
            if (state){     //paciente
                ui.pacientContainer.visibility = View.VISIBLE
                ui.doctorContainer.visibility = View.GONE
            }else{          //medico
                ui.pacientContainer.visibility = View.GONE
                ui.doctorContainer.visibility = View.VISIBLE
            }
        })
        //endregion

        //region <! Botão de criar uma nova conta !>
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
                                    contentView?.longSnackbar("Falha ao realizar login.")
                                }
                            }
                }
            }else{
                //medico
                val medico = Doctor()
                medico.cellphone = ui.cellPhoneEditText.text.toString()
                medico.email = ui.emailEditText.text.toString()
                medico.address = ui.addressEditText.text.toString()
                medico.crm = ui.crmEditText.text.toString()
                medico.phone = ui.telephoneEditText.text.toString()
                medico.name = ui.nameEditText.text.toString()
                medico.specialist = ui.specialistEditText.text.toString()
                Log.d("Médico", Gson().toJson(medico))

                //region <! Criando a conta no firebase !>
                doAsync {
                    mAuth.createUserWithEmailAndPassword(medico.email!!,  ui.passwordEditText.text.toString())
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
        //endregion

        //region <! Ao pressionar o botão de voltar !>
        ui.actionBar.backImageView.onClick {
            onBackPressed()
        }
        //endregion
    }
}