package br.com.waltervjunior.easymed

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context

class EasymedApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        @SuppressLint("StaticFieldLeak")
        var activity: Activity? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}