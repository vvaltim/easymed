package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.os.Bundle
import br.com.waltervjunior.easymed.model.Appointment
import br.com.waltervjunior.easymed.model.Doctor
import org.jetbrains.anko.setContentView

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

    }
}