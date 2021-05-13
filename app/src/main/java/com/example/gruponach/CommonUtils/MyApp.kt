package com.example.gruponach.CommonUtils

import android.app.Application
import android.content.Context

class MyApp : Application() {
    // Como en kotlin no existen variables staticas se reemplaza por compainion object que actua igual
    companion object {
        // se usa lateinit var para inidcar que la variable aun no se inicializa
        lateinit var instance: MyApp

        fun getContext(): Context {
            return instance;
        }

    }


    override fun onCreate() {
        super.onCreate()
        instance = this

    }

}
