package com.example.gruponach.ui.Movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gruponach.CommonUtils.Utils
import com.example.gruponach.R
import com.example.gruponach.Retrofit.Models.Movie
import com.example.gruponach.ui.Adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment() {

    private lateinit var movieViewModel: MoviesViewModel
    private lateinit var recycler: RecyclerView
    private lateinit var textErrorOffline:TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieViewModel =
            ViewModelProvider(this).get(MoviesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_movies, container, false)

        recycler = root.findViewById<RecyclerView>(R.id.recycler_movies)
        textErrorOffline = root.findViewById<TextView>(R.id.txtOffline)

        val manager = GridLayoutManager(context,2)
        recycler.layoutManager = manager

        val isOnline = context?.let { Utils.CheckInternetAcces(it) };

        if (isOnline!!){
            movieViewModel.GetMoviesOnline().observe(viewLifecycleOwner, Observer<List<Movie>>
            { movies ->
                if (movies != null) {
                    val adapter = MovieAdapter()
                    adapter.setData(movies)
                    recycler.adapter = adapter
                    movieViewModel.cleanData()
                    movieViewModel.saveMoviesTemporary(movies)
                }
            })

        }else{
            textErrorOffline.text = getString(R.string.no_internet)
            textErrorOffline.visibility = View.VISIBLE
            movieViewModel.GetMoviesOffline().observe(viewLifecycleOwner, Observer<List<Movie>>
            {moviesOffline ->
                val adapter = MovieAdapter()
                adapter.setData(moviesOffline)
                recycler.adapter = adapter

            })
        }



        return root
    }


    override fun onResume() {
        super.onResume()
        val isOnline = context?.let { Utils.CheckInternetAcces(it) };

        if (isOnline!!){
            textErrorOffline.visibility = View.GONE
            movieViewModel.GetMoviesOnline().observe(viewLifecycleOwner, Observer<List<Movie>>
            { movies ->
                if (movies != null) {
                    val adapter = MovieAdapter()
                    adapter.setData(movies)
                    recycler.adapter = adapter
                    movieViewModel.cleanData()
                    movieViewModel.saveMoviesTemporary(movies)
                }
            })

        }else{
            textErrorOffline.text = getString(R.string.no_internet)
            textErrorOffline.visibility = View.VISIBLE
            movieViewModel.GetMoviesOffline().observe(viewLifecycleOwner, Observer<List<Movie>>
            {moviesOffline ->
                val adapter = MovieAdapter()
                adapter.setData(moviesOffline)
                recycler.adapter = adapter

            })
        }

    }
}