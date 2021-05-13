package com.example.gruponach.DB.Repository

import androidx.lifecycle.MutableLiveData
import com.example.gruponach.CommonUtils.MyApp
import com.example.gruponach.DB.AppDataBase
import com.example.gruponach.DB.DAO.MovieDao
import com.example.gruponach.Retrofit.Models.Movie
import java.lang.Exception

class MovieDBOfflineRepository {

    private var movieDao: MovieDao
    private var movieOfflineList = MutableLiveData<List<Movie>>()

    init {
        val database: AppDataBase? = AppDataBase.getDatabase(MyApp.getContext())
        movieDao = database!!.movieDao()

    }

    fun getMoviesOffline(): MutableLiveData<List<Movie>> {
        movieOfflineList.postValue(movieDao.getMoviesOffline())
        return movieOfflineList
    }

    fun saveMoviesOffline(moviesOffline: List<Movie>) {
        movieDao.insertAllMovies(moviesOffline as ArrayList<Movie>)

    }

    fun cleanMovieData() {
        movieDao.deleteMoviesOffline()
    }
}