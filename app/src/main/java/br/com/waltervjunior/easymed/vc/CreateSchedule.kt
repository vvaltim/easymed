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
import br.com.waltervjunior.easymed.extension.snackbar
import br.com.waltervjunior.easymed.model.DetailedConfiguration
import br.com.waltervjunior.easymed.model.Schedule
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import org.jetbrains.anko.contentView
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast
import java.util.*
import kotlin.collections.ArrayList

class CreateSchedule : Activity() {
    companion object {
        const val PARTIAL_CONFIG = 1
    }
    lateinit var ui : CreateScheduleUi
    private var db = FirebaseFirestore.getInstance()
    var mSchedule : Schedule = Schedule()
    var mDayOfWeek = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        //region <! Definindo a interface !>
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
        //endregion

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
        val amInitialHourCalendar = Calendar.getInstance()
        ui.initMorningEditText.setOnClickListener {
            TimePickerDialog(this@CreateSchedule, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                amInitialHourCalendar.set(Calendar.HOUR_OF_DAY, hour)
                amInitialHourCalendar.set(Calendar.MINUTE, minute)
                ui.initMorningEditText.setText((amInitialHourCalendar.time.asString("HH:mm")))
                mSchedule.amHourInitial = amInitialHourCalendar.time
            }, amInitialHourCalendar.get(Calendar.HOUR_OF_DAY), amInitialHourCalendar.get(Calendar.MINUTE), true).show()
        }

        val amFinalHourCalendar = Calendar.getInstance()
        ui.finalMorningEditText.setOnClickListener {
            TimePickerDialog(this@CreateSchedule, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                amFinalHourCalendar.set(Calendar.HOUR_OF_DAY, hour)
                amFinalHourCalendar.set(Calendar.MINUTE, minute)
                ui.finalMorningEditText.setText((amFinalHourCalendar.time.asString("HH:mm")))
                mSchedule.amHourFinal = amFinalHourCalendar.time
            }, amFinalHourCalendar.get(Calendar.HOUR_OF_DAY), amFinalHourCalendar.get(Calendar.MINUTE), true).show()
        }
        //endregion

        //region <! Quando clicar no Tarde hora inicial e final !>
        val pmInitialHourCalendar = Calendar.getInstance()
        ui.initAfternoonEditText.setOnClickListener {
            TimePickerDialog(this@CreateSchedule, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                pmInitialHourCalendar.set(Calendar.HOUR_OF_DAY, hour)
                pmInitialHourCalendar.set(Calendar.MINUTE, minute)
                ui.initAfternoonEditText.setText((pmInitialHourCalendar.time.asString("HH:mm")))
                mSchedule.pmHourInitial = pmInitialHourCalendar.time
            }, pmInitialHourCalendar.get(Calendar.HOUR_OF_DAY), pmInitialHourCalendar.get(Calendar.MINUTE), true).show()
        }

        val pmFinalHourCalendar = Calendar.getInstance()
        ui.finalAfternoonEditText.setOnClickListener {
            TimePickerDialog(this@CreateSchedule, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                pmFinalHourCalendar.set(Calendar.HOUR_OF_DAY, hour)
                pmFinalHourCalendar.set(Calendar.MINUTE, minute)
                ui.finalAfternoonEditText.setText((pmFinalHourCalendar.time.asString("HH:mm")))
                mSchedule.pmHourFinal = pmFinalHourCalendar.time
            }, pmFinalHourCalendar.get(Calendar.HOUR_OF_DAY), pmFinalHourCalendar.get(Calendar.MINUTE), true).show()
        }
        //endregion

        //region <! Quando clicar em configurar parcialmente !>
        ui.partialConfigButton.onClick {
            mSchedule.interval = ui.intervalRadioGroup.checkedRadioButtonId
            if(isSchedulePeriods()){
                //chamar tela de definir as configurações personalizadas
                val intent = Intent(this@CreateSchedule, PartialConfigurationActivity::class.java)
                intent.putExtra("SCHEDULE", mSchedule)
                startActivityForResult(intent, PARTIAL_CONFIG)
            } else {
                //mostrar snackbar de preencyha as datas tal
                ui.scrollView.snackbar("É necessário preencher os periodos")
            }
        }
        //endregion

        //region <! Quando clicar  em salvar !>
        ui.createButton.onClick {
            if (mSchedule.detailedConfiguration == null)
                mSchedule.detailedConfiguration = DetailedConfiguration()
            when(scheduleType){
                "Configurar agenda padrão" -> {
                    //coloca todos os checkbox na variavel
                    checkBoxDayOfWeekendIsChecked()
                    Log.d("CheckBoxs", Gson().toJson(mDayOfWeek))
                    if(mDayOfWeek.isNotEmpty()){
                        mSchedule.detailedConfiguration?.days = mDayOfWeek
                        //salvar no firebase
                        indeterminateProgressDialog("Autenticando...") {
                            setCancelable(false)
                            setOnShowListener {
                                db.collection("schedules").add(mSchedule)
                                        .addOnSuccessListener { documentReference ->
                                            dismiss()
                                            mSchedule.id = documentReference.id

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
                    }else{
                        contentView?.longSnackbar("E necessário selecionar ao menos 1 dia da semana para salvar a agenda.")
                    }
                }
                "Configurar agenda diária" -> {
                    if (ui.dateEditText.text.isNotEmpty()) {
                        mSchedule.dateSchedule = ui.dateEditText.text.toString().getDate("dd/MM/yyyy")
                        mSchedule.detailedConfiguration?.days = null
                        Log.d("Agenda Diária", Gson().toJson(mSchedule))

                        //salvar no firebase
                        indeterminateProgressDialog("Autenticando...") {
                            setCancelable(false)
                            setOnShowListener {
                                db.collection("schedules").add(mSchedule)
                                        .addOnSuccessListener { documentReference ->
                                            dismiss()
                                            mSchedule.id = documentReference.id

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
                    }else{
                        contentView?.longSnackbar("E necessário inserir uma data para salvar a agenda.")
                    }
                }
            }
        }
        //endregion
    }

    //region <! Ao retornar da PartialConfiguration !>
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK){
            if (requestCode == PARTIAL_CONFIG){
                if (mSchedule.detailedConfiguration == null)
                    mSchedule.detailedConfiguration = DetailedConfiguration()
                mSchedule.detailedConfiguration?.ignoreHours = data?.getSerializableExtra("DETAILED_CONFIG") as ArrayList<String>
                Log.d("Detailed configuration", Gson().toJson(mSchedule.detailedConfiguration))
            }// else {
            //toast("BackButton pressed")
            //}
        }
    }
    //endregion

    //region <! Pegando os campos de periodo !>
    private fun isSchedulePeriods() : Boolean{
        return mSchedule.amHourInitial != null &&
                mSchedule.amHourFinal != null &&
                mSchedule.pmHourInitial != null &&
                mSchedule.pmHourFinal != null
    }
    //endregion

    //region <! Dias da semana selecionados !>
    fun checkBoxDayOfWeekendIsChecked(){
        if (ui.sundayCheckBox.isChecked)
            mDayOfWeek.add(ui.sundayCheckBox.text as String)
        else
            mDayOfWeek.remove(ui.sundayCheckBox.text as String)
        if(ui.mondayCheckBox.isChecked)
            mDayOfWeek.add(ui.mondayCheckBox.text as String)
        else
            mDayOfWeek.remove(ui.mondayCheckBox.text as String)
        if(ui.tuesdayCheckBox.isChecked)
            mDayOfWeek.add(ui.tuesdayCheckBox.text as String)
        else
            mDayOfWeek.remove(ui.tuesdayCheckBox.text as String)
        if(ui.wednesdayCheckBox.isChecked)
            mDayOfWeek.add(ui.wednesdayCheckBox.text as String)
        else
            mDayOfWeek.remove(ui.wednesdayCheckBox.text as String)
        if(ui.thursdayCheckBox.isChecked)
            mDayOfWeek.add(ui.thursdayCheckBox.text as String)
        else
            mDayOfWeek.remove(ui.thursdayCheckBox.text as String)
        if(ui.fridayCheckBox.isChecked)
            mDayOfWeek.add(ui.fridayCheckBox.text as String)
        else
            mDayOfWeek.remove(ui.fridayCheckBox.text as String)
        if(ui.saturdayCheckBox.isChecked)
            mDayOfWeek.add(ui.saturdayCheckBox.text as String)
        else
            mDayOfWeek.remove(ui.saturdayCheckBox.text as String)
    }
    //endregion
}