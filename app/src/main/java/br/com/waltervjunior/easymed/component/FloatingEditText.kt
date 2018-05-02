package br.com.waltervjunior.easymed.component

import android.content.Context
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.view.ViewManager
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import br.com.waltervjunior.easymed.R
import org.jetbrains.anko.AnkoViewDslMarker
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.design.textInputLayout
import org.jetbrains.anko.hintTextColor
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.textColor

fun ViewManager.floatingEditText(): FloatingEditText = floatingEditText {}
inline fun ViewManager.floatingEditText(init: (@AnkoViewDslMarker FloatingEditText).() -> Unit): FloatingEditText =
        ankoView({ ctx: Context -> FloatingEditText(ctx) }, theme = 0) { init(); lparams() }

class FloatingEditText(ctx : Context) : LinearLayout(ctx) {
    private var label : TextInputLayout
    private lateinit var field : TextInputEditText

    init {
        generateViewId()

        label = textInputLayout {
            generateViewId()
            lparams(matchParent)

            field = skTextInputEditText {
                hintTextColor = ContextCompat.getColor(context, R.color.greyLight)
                textColor = TextView(context).currentTextColor
            }
        }
    }

    //region <! GET/SET do EditText !>
    fun setValueEdit(value : String){
        this.field.setText(value)
    }
    fun getValueEdit() : String{
        return this.field.text.toString()
    }
    //endregion

    //region <! Adiciona/remove a mensagem de erro !>
    fun setError(value : String){
        this.label.error = value
    }
    fun removeError(){
        this.label.error = null
    }
    //endregion

    //region <! Definindo o label do campo !>
    fun setLabel(value : String){
        this.label.hint = value
    }
    //endregion

    //region <! Definindo os lparam !>
    fun lparams(
            width: Int = android.view.ViewGroup.LayoutParams.MATCH_PARENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            init: RelativeLayout.LayoutParams.() -> Unit
    ): FloatingEditText {
        val layoutParams = RelativeLayout.LayoutParams(width, height)
        layoutParams.init()
        this.layoutParams = layoutParams
        return this
    }

    fun lparams(
            width: Int = android.view.ViewGroup.LayoutParams.MATCH_PARENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT
    ): FloatingEditText {
        val layoutParams = RelativeLayout.LayoutParams(width, height)
        this.layoutParams = layoutParams
        return this
    }
    //endregion
}