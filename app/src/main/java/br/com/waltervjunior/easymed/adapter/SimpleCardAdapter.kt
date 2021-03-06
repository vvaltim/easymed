package br.com.waltervjunior.easymed.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.waltervjunior.easymed.vc.ScheduleAppointmentDoctor
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class SimpleCardAdapter(ctx : Context,private val dataset: ArrayList<Pair<String, String>>) : Adapter<SimpleCardAdapter.ViewHolder>() {
    private val mContext = ctx

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(SimpleCardAdapterUi().createView(AnkoContext.create(parent!!.context)))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = dataset[position]
        holder?.let {
            it.titleTextView.text = item.second
            it.card.onClick {
                val intent = Intent(mContext, ScheduleAppointmentDoctor::class.java)
                intent.putExtra("SPECIALIZATION", item)
                mContext.toast("${item.second}")
                mContext.startActivity(intent)
            }
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val titleTextView = itemView.find<TextView>(SimpleCardAdapterUi.ID_TITLE)
        val card = itemView.find<CardView>(SimpleCardAdapterUi.ID_CARD)
    }
}