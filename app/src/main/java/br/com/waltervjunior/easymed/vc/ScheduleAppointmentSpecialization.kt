package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.os.Bundle
import android.util.Log
import br.com.waltervjunior.easymed.adapter.SimpleCardAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast

class ScheduleAppointmentSpecialization : Activity() {
    //region <! Variaveis !>
    lateinit var ui : ScheduleAppointmentSpecializationUi
    var mSpecialization : ArrayList<Pair<String, String>> = ArrayList()
    var db = FirebaseFirestore.getInstance()
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        //region <! Criando o layout !>
        super.onCreate(savedInstanceState)
        ui = ScheduleAppointmentSpecializationUi()
        ui.setContentView(this)
        //endregion

        //region <! Eventos de atualizar !>
        ui.swipeRefreshLayout.setOnRefreshListener {
            updateSpecialization()
        }
        ui.swipeRefreshLayout.isRefreshing = true
        updateSpecialization()
        //endregion
    }

    //region <! Atualizando as especializações !>
    private fun updateSpecialization(){
        doAsync {
            db.collection("specialization").get().addOnCompleteListener {task ->
                if (task.isSuccessful){
                    for (document in task.result){
                        val temp = Pair(document.id, document.data["name"] as String)
                        mSpecialization.add(temp)
                    }
                    runOnUiThread {
                        ui.recyclerView.adapter = SimpleCardAdapter(this@ScheduleAppointmentSpecialization, mSpecialization)
                        ui.swipeRefreshLayout.isRefreshing = false
                    }
                } else {
                    runOnUiThread {
                        toast("Erro ao tentar pegar dados")
                        ui.swipeRefreshLayout.isRefreshing = false
                    }
                }
            }
        }
    }
    //endregion
}