package br.com.waltervjunior.easymed.component

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewManager
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.RelativeLayout
import android.widget.TextView
import br.com.waltervjunior.easymed.EasymedApplication
import br.com.waltervjunior.easymed.R
import br.com.waltervjunior.easymed.extension.generateViewId
import br.com.waltervjunior.easymed.extension.getActionBarSize
import br.com.waltervjunior.easymed.extension.setSelectableItemBackground
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.*

fun ViewManager.actionBar(): ActionBar = actionBar {}
inline fun ViewManager.actionBar(init: (@AnkoViewDslMarker ActionBar).() -> Unit): ActionBar =
        ankoView({ ctx: Context -> ActionBar(ctx) }, theme = 0) { init() }

class ActionBar(ctx : Context) : _RelativeLayout(ctx){

    private var leftAction: View? = null
    private var rightAction: View? = null
    private var internalSearchImageView: ImageView? = null
    private val actions: Stack<Pair<String, () -> Unit>> = Stack()
    val backImageView: ImageView
    val moreImageView: ImageView
    val searchImageView: ImageView
        get() = internalSearchImageView!!

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
        backImageView = addActionLeft(R.drawable.ic_back_white, show = false)
        /*backImageView.onClick {
            EasymedApplication.activity?.onBackPressed()
        }*/
        moreImageView = addActionRight(R.drawable.ic_more_white, show = false)
        moreImageView.onClick {
            //addAction(null, {})
            val popup = PopupMenu(ctx, moreImageView)
            val copy = Stack<Pair<String, () -> Unit>>()
            popup.setOnMenuItemClickListener({ item: MenuItem? ->
                var result = false
                copy.addAll(actions)
                while (copy.isNotEmpty()) {
                    val action = copy.pop()
                    if (action.first == item?.title) {
                        action.second()
                        result = true
                    }
                }
                result
            })
            copy.addAll(actions)
            while (copy.isNotEmpty()) {
                val action = copy.pop()
                popup.menu.add(action.first)
            }
            popup.show()
        }
        internalSearchImageView = addActionRight(R.drawable.ic_search_white, show = false)
        backgroundColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
        elevation = dip(4).toFloat()
        lparams(matchParent, getActionBarSize())
    }

    fun addActionLeft(resource: Int, onClickListener: OnClickListener? = null, show: Boolean = true): ImageView {
        val action = imageView(resource) {
            generateViewId()
            setSelectableItemBackground()
            setOnClickListener(onClickListener)
            leftPadding = dip(10)
            rightPadding = dip(10)
        }.lparams(dip(if (show) 44 else 0), matchParent) {
            if (leftAction == null) {
                alignParentStart()
            } else {
                rightOf(leftAction!!)
            }
        }
        (title.layoutParams as RelativeLayout.LayoutParams).addRule(RelativeLayout.END_OF, action.id)
        leftAction = action
        return action
    }

    fun addActionRight(resource: Int, onClickListener: OnClickListener? = null, show: Boolean = true): ImageView {
        val action = imageView(resource) {
            generateViewId()
            setSelectableItemBackground()
            setOnClickListener(onClickListener)
            leftPadding = dip(10)
            rightPadding = dip(10)
        }.lparams(dip(if (show) 44 else 0), matchParent) {
            if (rightAction == null) {
                alignParentEnd()
            } else {
                if (internalSearchImageView != null && internalSearchImageView == rightAction) {
                    leftOf(moreImageView)
                } else {
                    leftOf(rightAction!!)
                }
            }
        }
        if (internalSearchImageView != null) {
            (internalSearchImageView!!.layoutParams as RelativeLayout.LayoutParams).addRule(RelativeLayout.START_OF, action.id)
        } else {
            (title.layoutParams as RelativeLayout.LayoutParams).addRule(RelativeLayout.START_OF, action.id)
        }
        rightAction = action
        return action
    }

    fun hide(action: ImageView) {
        val params = action.layoutParams
        params.width = 0
        action.layoutParams = params
    }

    fun show(action: ImageView) {
        val params = action.layoutParams
        params.width = dip(44)
        action.layoutParams = params
    }

    fun onConfigurationChanged(activity: Activity) {
        val styledAttributes = activity.theme.obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
        val actionBarParams = layoutParams
        actionBarParams.height = styledAttributes.getDimension(0, 0f).toInt()
        layoutParams = actionBarParams
        styledAttributes.recycle()
    }

    /*fun addAction(name: String?, action: () -> Unit) {
        if (EasymedApplication.activity!!::class.java == EasymedApplication.MAIN_ACTIVITY && actions.isEmpty()) {
            actions.push(Pair(context.getString(R.string.action_bar_logout), { SKLoginService.doLogout(SKApplication.activity!!) }))
            actions.push(Pair(context.getString(R.string.action_bar_settings), { SKApplication.activity!!.startActivity(Intent(SKApplication.activity, SKSettingsActivity::class.java)) }))
        }
        if (name != null) {
            actions.push(Pair(name, action))
        }
    }*/
}