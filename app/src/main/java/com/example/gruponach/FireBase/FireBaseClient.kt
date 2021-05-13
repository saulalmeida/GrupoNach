package com.example.gruponach.FireBase

import android.os.Build
import android.util.Log
import com.example.gruponach.CommonUtils.Utils
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.util.Util
import com.google.firebase.ktx.Firebase

class FireBaseClient {

    companion object{
        private val instance = Firebase.firestore


        fun getInstance ()= instance

        fun addCoordinates(coord1:String, coord2:String){
            var result = false
            val coordinates = hashMapOf(
                "coord1" to coord1,
                "coord2" to coord2,
                "serialNumber" to Build.SERIAL,
                "date" to Utils.getDate()
            )

            instance.collection("coordinatesUser")
                .add(coordinates)
                .addOnSuccessListener{ documentReference ->
                    Log.e(FireBaseClient.toString(),"Documento agregado con Ud: ${documentReference.id}")
                    result = true
                  }
                .addOnFailureListener{ e -> Log.e(FireBaseClient.toString(),"Error al añadir documento")}
        }

        fun getLastCoordinateByUser(){
            instance.collection("coordinatesUser").orderBy("date")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.e(FireBaseClient.toString(), "${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e(FireBaseClient.toString(), "Error getting documents.", exception)
                }

        }
    }
}