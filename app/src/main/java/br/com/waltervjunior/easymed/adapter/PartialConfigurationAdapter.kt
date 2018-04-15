package br.com.waltervjunior.easymed.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onCheckedChange
import org.jetbrains.anko.sdk25.coroutines.onClick

/*class PartialConfigurationAdapter(ctx : Context, dataset: ArrayList<String>) : ArrayAdapter<String>(ctx, 0, dataset){
    private val mContext = AnkoContext.createReusable(ctx, this)
    var ignoreHours = ArrayList<String>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: PartialConfigurationAdapterUi().createView(mContext)
        val label = getItem(position)

        val checkBox = view.find<CheckBox>(PartialConfigurationAdapterUi.CHECKBOX)
        checkBox.text = label
        checkBox.onCheckedChange { _, isChecked ->
            if(isChecked){
                ignoreHours.remove(checkBox.text as String)
            }else{
                ignoreHours.add(checkBox.text as String)
            }
        }

        return view
    }
}*/

class PartialConfigurationAdapter(val dataset : ArrayList<String>) : Adapter<PartialConfigurationAdapter.ViewHolder>(){
    val ignoreHours = ArrayList<String>()

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = dataset[position]
        holder?.let{
            it.checkBox.text = item
            //it.checkBox.tag = position
            it.checkBox.isChecked = !ignoreHours.contains(item)

            it.checkBox.onClick {
                val teste = holder.checkBox.isChecked
                if(teste){     //descelecionado
                    ignoreHours.remove(item)
                }else{              //selecionado
                    ignoreHours.add(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(PartialConfigurationAdapterUi().createView(AnkoContext.create(parent!!.context)))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val checkBox = itemView.find<CheckBox>(PartialConfigurationAdapterUi.CHECKBOX)
    }
}