package br.com.waltervjunior.easymed.model

import br.com.waltervjunior.easymed.service.PreferenceService
import java.util.*

class Schedule (
    var amHourInitial : Int,
    var amHourFinal : Int,
    var pmHourInitial : Int,
    var pmHourFinal : Int,
    var interval : Int,
    var idDoctor: String = PreferenceService.getString("USER_ID")!!
){
    var id : String? = null
    var dateSchedule : Date? = null
    var detailedConfiguration : DetailedConfiguration? = null

    fun save(schedule: Schedule){

    }
}