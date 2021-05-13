package com.example.gruponach.ui.Upload

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gruponach.R

class UploadFragment : Fragment() {

    private lateinit var uploadViewModel: UploadViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        uploadViewModel =
            ViewModelProvider(this).get(UploadViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_upload, container, false)

        uploadViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }
}