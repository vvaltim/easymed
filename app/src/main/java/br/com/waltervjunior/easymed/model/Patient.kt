package br.com.waltervjunior.easymed.model

import br.com.waltervjunior.easymed.dao.FirebaseDB

class Patient (
        var name : String,
        var cellphone : String,
        var email : String,
        var dateOfBirth : String,
        var gender : String
){
    fun save(id : String){
        FirebaseDB().addPatient(this,  id)
    }
}