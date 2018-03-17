package br.com.waltervjunior.easymed.component

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.view.Gravity
import android.view.ViewManager
import android.widget.ImageView
import android.widget.TextView
import br.com.waltervjunior.easymed.EasymedApplication
import br.com.waltervjunior.easymed.R
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.sdk25.coroutines.onClick

fun ViewManager.actionBar(): ActionBar = actionBar {}
inline fun ViewManager.actionBar(init: (@AnkoViewDslMarker ActionBar).() -> Unit): ActionBar =
        ankoView({ ctx: Context -> ActionBar(ctx) }, theme = 0) { init() }

class ActionBar(ctx : Context) : _RelativeLayout(ctx){

    val backImageView: ImageView

    val title: TextView = textView {
        generateViewId()
        setTextColor(Color.WHITE)
        textSize = 18f
        leftPadding = dip(10)
        rightPadding = dip(10)
        maxLines = 1
        ellipsize = TextUtils.TruncateAt.END
        gravity = Gravity.CENTER_VERTICAL
    }.lparams(matchParent, matchParent)

    init {
        generateViewId()
        backImageView = imageView (R.drawable.ic_back_image){

        }

    }
}