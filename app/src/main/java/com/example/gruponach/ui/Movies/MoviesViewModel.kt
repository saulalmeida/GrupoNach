package com.example.gruponach.ui.Movies

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gruponach.DB.Repository.MovieDBOfflineRepository
import com.example.gruponach.Retrofit.Models.Movie
import com.example.gruponach.Retrofit.Repository.MovieDBRepository

class MoviesViewModel : ViewModel() {

    private val RestRepository :MovieDBRepository
    private val OfflineRepository :MovieDBOfflineRepository
    private var listMovies = MutableLiveData<List<Movie>>()
    private var listMoviesOffline = MutableLiveData<List<Movie>>()

    init {
        RestRepository = MovieDBRepository()
        OfflineRepository = MovieDBOfflineRepository()

        listMovies = RestRepository.popularMovies()!!

    }

    fun GetMoviesOnline () :MutableLiveData<List<Movie>>{
        return listMovies
    }

    fun GetMoviesOffline(): MutableLiveData<List<Movie>> {
        listMoviesOffline =OfflineRepository.getMoviesOffline()
        return  listMoviesOffline
    }

    fun saveMoviesTemporary(moviesOffline:List<Movie>){
        OfflineRepository.saveMoviesOffline(moviesOffline)
    }

    fun cleanData() {
        OfflineRepository.cleanMovieData()
    }

}