package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.setContentView

class MySchedule : Activity() {
    lateinit var ui : MyScheduleUi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = MyScheduleUi()
        ui.setContentView(this)

        //region <! Ao pressionar o backButton !>
        ui.actionBar.backImageView.onClick {
            onBackPressed()
        }
        //endregion


    }
}