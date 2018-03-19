package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.setContentView

class MainActivity : Activity(){

    lateinit var ui : MainActivityUi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //definindo a view
        ui = MainActivityUi()
        ui.setContentView(this)

        ui.sairButton.onClick {
            //efetua logout do firebase
            FirebaseAuth.getInstance().signOut()

            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK and Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

    }
}
