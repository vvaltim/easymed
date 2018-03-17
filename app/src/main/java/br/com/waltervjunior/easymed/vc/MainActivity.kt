package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
            onBackPressed()
        }

    }
}
