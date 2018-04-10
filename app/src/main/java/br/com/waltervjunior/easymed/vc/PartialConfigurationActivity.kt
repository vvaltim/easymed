package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import br.com.waltervjunior.easymed.adapter.PartialConfigurationAdapter
import br.com.waltervjunior.easymed.extension.addMinutes
import br.com.waltervjunior.easymed.extension.asString
import br.com.waltervjunior.easymed.model.Schedule
import com.google.gson.Gson
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.setContentView
import java.util.*

class PartialConfigurationActivity : Activity(){
    lateinit var ui : PartialConfigurationActivityUi
    var mHours = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = PartialConfigurationActivityUi()
        ui.setContentView(this)

        //region <! Preenchendo com os valores vindo do intent !>
        val schedule = intent.getSerializableExtra("SCHEDULE") as? Schedule
        if (schedule != null){
            //adicionando o tempo do am e pm
            addHoursToArraylist(schedule.amHourInitial!!, schedule.amHourFinal!!, schedule.interval!!)
            addHoursToArraylist(schedule.pmHourInitial!!, schedule.pmHourFinal!!, schedule.interval!!)

            ui.listView.adapter = PartialConfigurationAdapter(this@PartialConfigurationActivity, mHours)
        }
        //endregion

        //region <! Ao pressionar o backButton !>
        ui.actionBar.backImageView.onClick {
            onBackPressed()
        }
        //endregion

        //region <! Ao precionar o botão de salvar !>
        ui.saveImageView.onClick {
            Log.d("Horas ignoradas", Gson().toJson((ui.listView.adapter as PartialConfigurationAdapter).ignoreHours))
            val intent = Intent()
            intent.putExtra("DETAILED_CONFIG", (ui.listView.adapter as PartialConfigurationAdapter).ignoreHours)
            setResult(RESULT_OK, intent)
            onBackPressed()
        }
        //endregion
    }
    //region <! Adicionando os intervalos na ArrayList !>
    private fun addHoursToArraylist(init : Date, final : Date, interval : Int){
        var hourTemp = init
        while (hourTemp < final){
            mHours.add(hourTemp.asString("hh:mm"))
            Log.d("Hora adicionada", hourTemp.asString("hh:mm"))
            hourTemp = hourTemp.addMinutes(interval)
        }
    }
    //endregion
}