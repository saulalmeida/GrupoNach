package com.example.gruponach.ui.Maps

import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gruponach.FireBase.FireBaseClient
import com.example.gruponach.FireBase.LocationFirebase
import com.example.gruponach.Retrofit.Models.Movie

class MapsViewModel : ViewModel() {

    private var currentLocation = MutableLiveData<LocationFirebase>()
    private val firebaseClient = FireBaseClient.getInstance()

    init {

    }

    fun getCurrentLocation(): MutableLiveData<LocationFirebase> {
        try {
            firebaseClient.collection("coordinatesUser").orderBy("date")
                .get()
                .addOnSuccessListener { result ->
                    val resultData = result.last()
                    val Serial = resultData.get("serialNumber").toString()
                    if (Serial!!.equals(Build.SERIAL)) {
                        val date = resultData.get("date").toString()
                        val serialNumber = resultData.get("serialNumber").toString()
                        //cord2 = Lattitude
                        val coord2 = resultData.get("coord2").toString().toDouble()
                        //cord1 = Altitude
                        val coord1 = resultData.get("coord1").toString().toDouble()
                        val lastLocation = LocationFirebase(coord1, coord2, serialNumber, date)
                        currentLocation.value =lastLocation
                    }

                    Log.e(FireBaseClient.toString(), " ${resultData.data}")


                }
                .addOnFailureListener { exception ->
                    Log.e(FireBaseClient.toString(), "Error getting documents.", exception)
                }
        } catch (exc: Exception) {

        }

        return currentLocation

    }

//    fun initTimer(){
//        val timer = object: CountDownTimer(20000, 1000) {
//            override fun onTick(millisUntilFinished: Long) {...}
//
//            override fun onFinish() {...}
//        }
//        timer.start()
//    }
}