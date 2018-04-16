package br.com.waltervjunior.easymed.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class SimpleCardAdapter(private val dataset: ArrayList<Pair<String, String>>) : Adapter<SimpleCardAdapter.ViewHolder>() {
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
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val titleTextView = itemView.find<TextView>(SimpleCardAdapterUi.ID_TITLE)
    }
}