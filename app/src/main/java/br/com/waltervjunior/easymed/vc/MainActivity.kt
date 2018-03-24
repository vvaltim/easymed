package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.waltervjunior.easymed.EasymedApplication
import br.com.waltervjunior.easymed.R
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.selector
import org.jetbrains.anko.setContentView

class MainActivity : Activity(){

    lateinit var ui : MainActivityUi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //definindo a view
        ui = MainActivityUi()
        ui.setContentView(this)
        EasymedApplication.activity = this

        ui.sairButton.onClick {
            //efetua logout do firebase
            FirebaseAuth.getInstance().signOut()

            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK and Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        ui.createScheduleButton.onClick {
            selector("Qual agenda deseja configurar?", listOf("Agenda padrão", "Agenda diaria")){ _, i ->
                val intent = Intent(this@MainActivity, CreateSchedule::class.java)
                when(i){
                    0 -> {
                        intent.putExtra("SCHEDULE_TYPE","Configurar agenda padrão")
                        startActivity(intent)
                    }
                    1 -> {
                        intent.putExtra("SCHEDULE_TYPE","Configurar agenda diária")
                        startActivity(intent)
                    }
                }
            }
        }
    }
}
