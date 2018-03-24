package br.com.waltervjunior.easymed.extension

import android.util.TypedValue
import android.view.View
import br.com.waltervjunior.easymed.R
import org.jetbrains.anko.backgroundResource

/**
 * Created by Walter on 23/03/2018.
 */

fun <T : View> T.setSelectableItemBackground(): T {
    val typedValue = TypedValue()
    context.theme.resolveAttribute(R.attr.selectableItemBackground, typedValue, true)
    this.backgroundResource = typedValue.resourceId
    return this
}

fun <T : View> T.setSelectableItemBackgroundBorderless(): T {
    val typedValue = TypedValue()
    context.theme.resolveAttribute(R.attr.selectableItemBackgroundBorderless, typedValue, true)
    this.backgroundResource = typedValue.resourceId
    return this
}

fun <T : View> T.generateViewId(): T {
    this.id = View.generateViewId()
    return this
}
fun <T : View> T.getActionBarSize(): Int {
    val actionBarSize = TypedValue()
    context.theme.resolveAttribute(R.attr.actionBarSize, actionBarSize, true)
    return TypedValue.complexToDimensionPixelSize(actionBarSize.data, resources.displayMetrics)
}
