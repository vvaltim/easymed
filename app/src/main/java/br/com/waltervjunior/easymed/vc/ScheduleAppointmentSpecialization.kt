package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.os.Bundle
import android.util.Log
import br.com.waltervjunior.easymed.adapter.SimpleCardAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast

class ScheduleAppointmentSpecialization : Activity() {
    lateinit var ui : ScheduleAppointmentSpecializationUi
    var mSpecialization : ArrayList<Pair<String, String>> = ArrayList()
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ScheduleAppointmentSpecializationUi()
        ui.setContentView(this)

        db.collection("specialization").get().addOnCompleteListener {task ->
            if (task.isSuccessful){
                for (document in task.result){
                    val temp = Pair(document.id, document.data["name"] as String)
                    mSpecialization.add(temp)
                }
                ui.recyclerView.adapter = SimpleCardAdapter(mSpecialization)
            } else {
                toast("Erro ao tentar pegar dados")
            }
        }
    }
}