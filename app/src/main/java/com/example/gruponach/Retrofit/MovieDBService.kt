package com.example.gruponach.Retrofit

import com.example.gruponach.Retrofit.Models.PopularMovies
import retrofit2.Call
import retrofit2.http.GET

interface MovieDBService {

    @GET("movie/popular")
    fun getPopularMovies(): Call<PopularMovies>

}