package br.com.waltervjunior.easymed.model

import br.com.waltervjunior.easymed.dao.FirebaseDB
import java.io.Serializable

class Doctor : Serializable {
    var id : String? = null
    var name : String? = null
    var crm : String? = null
    var address : String? = null
    var phone : String? = null
    var cellphone : String? = null
    var email : String? = null
    var specialist : String? = null

    fun save(id : String){
        this.id = id
        FirebaseDB().addDoctor(this, id)
    }
}