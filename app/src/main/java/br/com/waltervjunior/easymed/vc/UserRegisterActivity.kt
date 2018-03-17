package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.setContentView

class UserRegisterActivity : Activity(){
    lateinit var ui : UserRegisterActivityUi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = UserRegisterActivityUi()
        ui.setContentView(this)


        ui.backButton.onClick {
            onBackPressed()
        }
        ui.userToggleButton.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, state ->
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
            val intent = Intent(this@UserRegisterActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}