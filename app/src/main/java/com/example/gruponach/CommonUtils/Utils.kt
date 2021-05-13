package com.example.gruponach.CommonUtils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object{
        fun getDate(): String {
            val pattern = "dd/MM/yyyy HH:mm:ss"
            val simpleDateFormat = SimpleDateFormat(pattern)
            val date: String = simpleDateFormat.format(Date())
            return date
        }

        fun CheckInternetAcces(context: Context) :Boolean{
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

            return activeNetwork?.isConnectedOrConnecting == true
        }
    }
}