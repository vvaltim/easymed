package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import br.com.waltervjunior.easymed.extension.asString
import br.com.waltervjunior.easymed.extension.getDate
import br.com.waltervjunior.easymed.model.Appointment
import br.com.waltervjunior.easymed.model.Doctor
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.setContentView
import java.util.*

class ScheduleAppointmentHours : Activity() {
    lateinit var ui : ScheduleAppointmentHoursUi
    val appointment = Appointment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ScheduleAppointmentHoursUi()
        ui.setContentView(this)

        //pegnado o medico selecionado anteriormente
        val doctor = intent.getSerializableExtra("DOCTOR") as? Doctor
        if (doctor != null){
            ui.nameTextView.text = doctor.name
            ui.crmTextView.text = doctor.crm
            ui.streetTextView.text = doctor.address
            ui.phoneTextView.text = doctor.phone
            ui.cellphoneTextView.text = doctor.cellphone
            ui.emailTextView.text = doctor.email
        }


        ui.dateAppointment.field.onClick {
            val calendar = Calendar.getInstance()
            DatePickerDialog(this@ScheduleAppointmentHours, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                //TODO:Carregar o serviço para verificar horas disponíveis nodia selecionado
                //se o valor selecionado tiver consulta, já seta a data
                ui.dateAppointment.setValueEdit((calendar.time.asString("dd/MM/yyyy")))
                appointment.date = ui.dateAppointment.getValueEdit().getDate("dd/MM/yyyy")
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

    }
}