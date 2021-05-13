package com.example.gruponach.Retrofit

import com.example.gruponach.CommonUtils.Constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieDBClient {

    private var theMovieDbService : MovieDBService
    private val retrofit: Retrofit

    companion object {
        var instance :MovieDBClient? = null
            get() {
                if(field == null){
                    instance = MovieDBClient()
                }
                return field
            }
    }

    init {
        // incluir interceptor que definimos
        val okHttpClientBuilder  = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(MovieDBInterceptor())

        val cliente = okHttpClientBuilder.build()

        // Cosntruir cliente
        retrofit = Retrofit.Builder()
            .baseUrl(Constantes.TMBDAPI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(cliente)
            .build()

        // instanciamos el servicio de retrofit apartir del objeto retrofit
        theMovieDbService = retrofit.create(MovieDBService::class.java)

    }

    fun getTheMovieDbService() = theMovieDbService

}