package com.example.gruponach.ui.Maps

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gruponach.FireBase.LocationFirebase
import com.example.gruponach.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception

class MapFragment : Fragment() {

    private lateinit var mapsViewModel: MapsViewModel
    private var IsMapReady = false
    private lateinit var googleMap:GoogleMap ;

    private val callback = OnMapReadyCallback { googleMap ->

        IsMapReady = true
        this.googleMap = googleMap

//        val sydney = LatLng(-34.0, 151.0)
//        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        mapsViewModel =
            ViewModelProvider(this).get(MapsViewModel::class.java)

        mapsViewModel.getCurrentLocation().observe(viewLifecycleOwner, Observer<LocationFirebase> {
            if (IsMapReady){
                printNewLocation(it)
            }

        })
    }

    fun printNewLocation(location:LocationFirebase){
        try {
            googleMap.setMinZoomPreference(6.0f)
            googleMap.setMinZoomPreference(14.0f)
            val miLocation = LatLng(location.altitud, location.latitud)
            googleMap.addMarker(MarkerOptions().position(miLocation).title("Última ubicación : ${location.date}"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(miLocation))
        }
        catch (exc:Exception){

        }

    }
}