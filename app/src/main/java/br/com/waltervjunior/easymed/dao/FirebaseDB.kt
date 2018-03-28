package br.com.waltervjunior.easymed.dao

import android.util.Log
import br.com.waltervjunior.easymed.model.Doctor
import br.com.waltervjunior.easymed.model.Patient
import br.com.waltervjunior.easymed.model.Schedule
import com.google.firebase.firestore.FirebaseFirestore


class FirebaseDB {
    var db = FirebaseFirestore.getInstance()

    fun addDoctor(doctor: Doctor, id: String){
        db.collection("doctors").document(id)
                .set(doctor).addOnSuccessListener {
                    Log.d("Add Doctor", "Salvo com sucesso")
                    //salvar esse id para que seja possivel fazer pesquisas
                }
                .addOnFailureListener { e ->
                    Log.w("Add Doctor", "Error adding document", e)
                }

    }

    fun addPatient(patient: Patient, id : String){
        db.collection("patients").document(id)
                .set(patient).addOnSuccessListener {
                    Log.d("Add Patient", "Salvo com sucesso")
                    //salvar esse id para que seja possivel fazer pesquisas
                }
                .addOnFailureListener { e ->
                    Log.w("Add Patient", "Error adding document", e)
                }

    }
}