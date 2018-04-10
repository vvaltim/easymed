package br.com.waltervjunior.easymed.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onCheckedChange

class PartialConfigurationAdapter(ctx : Context, dataset: ArrayList<String>) : ArrayAdapter<String>(ctx, 0, dataset){
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

        //metodo para registrar os que não estão registrados


        return view
    }
}