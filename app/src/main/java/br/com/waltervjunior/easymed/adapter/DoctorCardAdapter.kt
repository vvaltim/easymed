package br.com.waltervjunior.easymed.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.waltervjunior.easymed.model.Doctor
import br.com.waltervjunior.easymed.vc.ScheduleAppointmentDoctor
import br.com.waltervjunior.easymed.vc.ScheduleAppointmentHours
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class DoctorCardAdapter(ctx : Context, private val dataset: ArrayList<Doctor>) : Adapter<DoctorCardAdapter.ViewHolder>() {
    private val mContext = ctx

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(DoctorCardAdapterUi().createView(AnkoContext.create(parent!!.context)))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = dataset[position]
        holder?.let {
            it.titleTextView.text = item.name
            it.descriptionTextView.text = item.address
            it.card.onClick {
                val intent = Intent(mContext, ScheduleAppointmentHours::class.java)
                intent.putExtra("DOCTOR", item)
                mContext.toast("${item.name}")
                mContext.startActivity(intent)
            }
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val card = itemView.find<CardView>(DoctorCardAdapterUi.ID_CARD)
        val titleTextView = itemView.find<TextView>(DoctorCardAdapterUi.ID_TITLE)
        val descriptionTextView = itemView.find<TextView>(DoctorCardAdapterUi.ID_DESCRIPTION)
    }
}