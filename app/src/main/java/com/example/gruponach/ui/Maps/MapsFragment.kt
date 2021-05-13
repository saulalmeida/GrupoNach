package com.example.gruponach.ui.Maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gruponach.R

class MapsFragment : Fragment() {

    private lateinit var mapsViewModel: MapsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mapsViewModel =
            ViewModelProvider(this).get(MapsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_maps, container, false)

        mapsViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }
}