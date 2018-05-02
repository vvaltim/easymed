package br.com.waltervjunior.easymed.model

import java.io.Serializable
import java.util.*

class Appointment : Serializable{
    companion object {
        const val PENDING = 1
        const val COMPLETED = 2
        const val CANCELED = 3
    }
    var id : String? = null
    var patient : Patient? = null
    var doctor : Doctor? = null
    var date : Date? = null
    var convenio : String? = null
    var observation : String? = null
    var status = PENDING
}