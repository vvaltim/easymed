package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.os.Bundle
import br.com.waltervjunior.easymed.adapter.DoctorCardAdapter
import br.com.waltervjunior.easymed.model.Doctor
import org.jetbrains.anko.setContentView

class ScheduleAppointmentDoctor : Activity() {
    lateinit var ui : ScheduleAppointmentDoctorUi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ScheduleAppointmentDoctorUi()
        ui.setContentView(this)


        //region <! Pegando os médicos da expecialidade passada e colocando no adaptador !>
        //TODO:Pegar do firebase
        val doctors = ArrayList<Doctor>()

        val d1 = Doctor()
        d1.name = "Fulano Tal"
        d1.crm = "123.456"
        d1.address = "Clinica Tal"
        d1.phone = "(34) 384123454"
        d1.cellphone = "(34) 991234567"
        d1.email = "fulano@clinaca.tal"
        doctors.add(d1)

        val d2 = Doctor()
        d2.address = "Clinica Outra"
        d2.name = "Fulano Outro"
        doctors.add(d2)

        ui.recyclerView.adapter = DoctorCardAdapter(this@ScheduleAppointmentDoctor, doctors)
        //endregion
    }
}