package br.com.waltervjunior.easymed.model

import br.com.waltervjunior.easymed.dao.FirebaseDB

class Doctor (
        var name : String,
        var crm : String,
        var address : String,
        var phone : String,
        var cellphone : String,
        var email : String,
        var specialist : String
        ){
        fun save(id : String){
                FirebaseDB().addDoctor(this, id)
        }
}