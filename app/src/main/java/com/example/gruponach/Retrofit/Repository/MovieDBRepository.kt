package com.example.gruponach.Retrofit.Repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.gruponach.CommonUtils.MyApp
import com.example.gruponach.Retrofit.Models.Movie
import com.example.gruponach.Retrofit.Models.PopularMovies
import com.example.gruponach.Retrofit.MovieDBClient
import com.example.gruponach.Retrofit.MovieDBService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDBRepository {
    var theMovieDBService : MovieDBService? = null
    var theMovieDBClient : MovieDBClient? = null
    private var popularMovies : MutableLiveData<List<Movie>>? = null

    init {
        theMovieDBClient = MovieDBClient.instance
        theMovieDBService = theMovieDBClient?.getTheMovieDbService()
        popularMovies = popularMovies()


    }

    fun popularMovies(): MutableLiveData<List<Movie>>?{
        if (popularMovies == null){
            popularMovies = MutableLiveData<List<Movie>>()

        }
        val call : Call<PopularMovies>? =  theMovieDBService?.getPopularMovies()
        call?.enqueue(object : Callback<PopularMovies> {
            override fun onResponse(
                call: Call<PopularMovies>,
                response: Response<PopularMovies>
            ) {
                if (response.isSuccessful){
                    popularMovies?.value = response.body()?.results
                }

            }

            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                Toast.makeText(MyApp.instance,"Error en la llamada", Toast.LENGTH_LONG).show()
            }

        })

        return  popularMovies

    }
}