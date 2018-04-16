package br.com.waltervjunior.easymed.model

import br.com.waltervjunior.easymed.service.PreferenceService
import java.io.Serializable
import java.util.*

class Schedule ( var idDoctor: String = PreferenceService.getString("USER_ID")!! ) : Serializable{
    var amHourInitial : Date? = null
    var amHourFinal : Date? = null
    var pmHourInitial : Date? = null
    var pmHourFinal : Date? = null
    var interval : Int? = null
    var id : String? = null
    var dateSchedule : Date? = null
    var detailedConfiguration : DetailedConfiguration? = null
}