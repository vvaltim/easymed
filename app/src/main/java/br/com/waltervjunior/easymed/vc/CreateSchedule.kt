package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import br.com.waltervjunior.easymed.extension.asString
import br.com.waltervjunior.easymed.extension.getDate
import br.com.waltervjunior.easymed.extension.longSnackbar
import br.com.waltervjunior.easymed.model.Schedule
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import org.jetbrains.anko.contentView
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.setContentView
import java.util.*

class CreateSchedule : Activity() {
    lateinit var ui : CreateScheduleUi
    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = CreateScheduleUi()
        ui.setContentView(this)

        val scheduleType : String = intent.getStringExtra("SCHEDULE_TYPE")
        ui.actionBar.title.text = scheduleType
        ui.actionBar.backImageView.onClick { onBackPressed() }
        when(scheduleType){
            "Configurar agenda padrão" -> {
                //mostrar os dias da semana
                ui.dayOfWeekTextView.visibility = View.VISIBLE
                ui.dayOfWeekContainer.visibility = View.VISIBLE
                //ocultar a data
                ui.dateTextView.visibility = View.GONE
                ui.dateEditText.visibility = View.GONE
            }
            "Configurar agenda diária" -> {
                //mostrar os dias da semana
                ui.dayOfWeekTextView.visibility = View.GONE
                ui.dayOfWeekContainer.visibility = View.GONE
                //ocultar a data
                ui.dateTextView.visibility = View.VISIBLE
                ui.dateEditText.visibility = View.VISIBLE
            }
        }

        //region <! Quando clicar no dia específico !>
        var dateCalendar = Calendar.getInstance()
        ui.dateEditText.setOnClickListener {
            val dateDatePickerDialog = DatePickerDialog(this@CreateSchedule, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                dateCalendar.set(Calendar.YEAR, year)
                dateCalendar.set(Calendar.MONTH, month)
                dateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                ui.dateEditText.setText((dateCalendar.time.asString("dd/MM/yyyy")))
                //salvar aqui
            }, dateCalendar.get(Calendar.YEAR), dateCalendar.get(Calendar.MONTH), dateCalendar.get(Calendar.DAY_OF_MONTH))
            dateDatePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Limpar", { _, _ ->
                dateCalendar = Calendar.getInstance()
                ui.dateEditText.text.clear()
                //limpar aqui
            })
            dateDatePickerDialog.show()
        }
        //endregion

        //region <! Quando clicar no Manha hora inicial e final !>
        /*var amInitialHourCalendar = Calendar.getInstance()
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
        }*/
        //endregion

        //region <! Quando clicar no Tarde hora inicial e final !>
        /*var pmInitialHourCalendar = Calendar.getInstance()
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
        }*/
        //endregion

        //region <! Quando clicar  em salvar !>
        ui.createButton.onClick {
            when(scheduleType){
                "Configurar agenda padrão" -> {

                }
                "Configurar agenda diária" -> {
                    val agendaDiaria = Schedule( amHourInitial = ui.initMorningEditText.text.toString().toInt(),
                            amHourFinal = ui.finalMorningEditText.text.toString().toInt(),
                            pmHourInitial = ui.initAfternoonEditText.text.toString().toInt(),
                            pmHourFinal = ui.finalAfternoonEditText.text.toString().toInt(),
                            interval = ui.intervalRadioGroup.checkedRadioButtonId)
                    agendaDiaria.dateSchedule = ui.dateEditText.text.toString().getDate("dd/MM/yyyy")
                    Log.d("Agenda Diária", Gson().toJson(agendaDiaria))

                    //salvar no firebase
                    indeterminateProgressDialog("Autenticando..."){
                        setCancelable(false)
                        setOnShowListener {
                            db.collection("schedules").add(agendaDiaria)
                                    .addOnSuccessListener {documentReference ->
                                        dismiss()
                                        agendaDiaria.id = documentReference.id

                                        //informar que a agenda foi salva com sucesso e retornar pra dashboard
                                        contentView?.longSnackbar("Agenda salva com sucesso.")
                                        startActivity(Intent(this@CreateSchedule, MainActivity::class.java))
                                    }.addOnFailureListener {
                                        dismiss()

                                        //informar que a agenda deu pau
                                        contentView?.longSnackbar("Não foi possível salvar a agenda. Tente novamente mais tarde.")
                                    }
                        }
                    }
                }
            }
        }
        //endregion
    }
}