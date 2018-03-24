package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import br.com.waltervjunior.easymed.extension.asString
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.setContentView
import java.util.*

class CreateSchedule : Activity() {
    lateinit var ui : CreateScheduleUi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = CreateScheduleUi()
        ui.setContentView(this)
        val scheduleType : String = intent.getStringExtra("SCHEDULE_TYPE")
        ui.actionBar.title.text = scheduleType
        ui.actionBar.backImageView.onClick { onBackPressed() }

        //region <! Quando clicar no Manha hora inicial e final !>
        var amInitialHourCalendar = Calendar.getInstance()
        ui.initMorningEditText.setOnClickListener {
            val amInitialHourTimePickerDialog = TimePickerDialog(this@CreateSchedule, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                amInitialHourCalendar.set(Calendar.HOUR_OF_DAY, hour)
                amInitialHourCalendar.set(Calendar.MINUTE, minute)
                ui.initMorningEditText.setText((amInitialHourCalendar.time.asString("HH:mm")))
                //mModel.horaInicial = amInitialHourCalendar.time.asString("HH:mm")
            }, amInitialHourCalendar.get(Calendar.HOUR_OF_DAY), amInitialHourCalendar.get(Calendar.MINUTE), true)
            amInitialHourTimePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Limpar", { _, _ ->
                amInitialHourCalendar = Calendar.getInstance()
                ui.initMorningEditText.text.clear()
                //mModel.horaInicial = ""
            })
            amInitialHourTimePickerDialog.show()
        }

        var amFinalHourCalendar = Calendar.getInstance()
        ui.finalMorningEditText.setOnClickListener {
            val amFinalHourTimePickerDialog = TimePickerDialog(this@CreateSchedule, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                amFinalHourCalendar.set(Calendar.HOUR_OF_DAY, hour)
                amFinalHourCalendar.set(Calendar.MINUTE, minute)
                ui.finalMorningEditText.setText((amFinalHourCalendar.time.asString("HH:mm")))
                //mModel.horaInicial = amFinalHourCalendar.time.asString("HH:mm")
            }, amFinalHourCalendar.get(Calendar.HOUR_OF_DAY), amFinalHourCalendar.get(Calendar.MINUTE), true)
            amFinalHourTimePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Limpar", { _, _ ->
                amFinalHourCalendar = Calendar.getInstance()
                ui.finalMorningEditText.text.clear()
                //mModel.horaInicial = ""
            })
            amFinalHourTimePickerDialog.show()
        }
        //endregion

        //region <! Quando clicar no Tarde hora inicial e final !>
        var pmInitialHourCalendar = Calendar.getInstance()
        ui.initAfternoonEditText.setOnClickListener {
            val pmInitialHourTimePickerDialog = TimePickerDialog(this@CreateSchedule, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                pmInitialHourCalendar.set(Calendar.HOUR_OF_DAY, hour)
                pmInitialHourCalendar.set(Calendar.MINUTE, minute)
                ui.initAfternoonEditText.setText((pmInitialHourCalendar.time.asString("HH:mm")))
                //mModel.horaInicial = pmInitialHourCalendar.time.asString("HH:mm")
            }, pmInitialHourCalendar.get(Calendar.HOUR_OF_DAY), pmInitialHourCalendar.get(Calendar.MINUTE), true)
            pmInitialHourTimePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Limpar", { _, _ ->
                pmInitialHourCalendar = Calendar.getInstance()
                ui.initAfternoonEditText.text.clear()
                //mModel.horaInicial = ""
            })
            pmInitialHourTimePickerDialog.show()
        }

        var pmFinalHourCalendar = Calendar.getInstance()
        ui.finalAfternoonEditText.setOnClickListener {
            val pmFinalHourTimePickerDialog = TimePickerDialog(this@CreateSchedule, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                pmFinalHourCalendar.set(Calendar.HOUR_OF_DAY, hour)
                pmFinalHourCalendar.set(Calendar.MINUTE, minute)
                ui.finalAfternoonEditText.setText((pmFinalHourCalendar.time.asString("HH:mm")))
                //mModel.horaInicial = pmFinalHourCalendar.time.asString("HH:mm")
            }, pmFinalHourCalendar.get(Calendar.HOUR_OF_DAY), pmFinalHourCalendar.get(Calendar.MINUTE), true)
            pmFinalHourTimePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Limpar", { _, _ ->
                pmFinalHourCalendar = Calendar.getInstance()
                ui.finalAfternoonEditText.text.clear()
                //mModel.horaInicial = ""
            })
            pmFinalHourTimePickerDialog.show()
        }
        //endregion
    }
}