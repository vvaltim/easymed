package br.com.waltervjunior.easymed.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class PartialConfigurationAdapter(ctx : Context, dataset: ArrayList<String>) : ArrayAdapter<String>(ctx, 0, dataset){
    private val mContext = AnkoContext.createReusable(ctx, this)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: PartialConfigurationAdapterUi().createView(mContext)
        val label = getItem(position)

        val checkBox = view.find<CheckBox>(PartialConfigurationAdapterUi.CHECKBOX)
        checkBox.text = label

        return view
    }
}