package com.example.gruponach.Retrofit

import com.example.gruponach.CommonUtils.Constantes
import okhttp3.Interceptor
import okhttp3.Response

class MovieDBInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        // añadir parametros a la URL de la cadena que recibimos (chain)

        val urlWithParams = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(Constantes.URL_PARAM_API_KEY, Constantes.API_KEY)
            .addQueryParameter(Constantes.URL_PARAM_LANGUAGE, "es-Es")
            .build()

        var request = chain.request()

        request = request.newBuilder()
            .url(urlWithParams)
            .addHeader("Content-Type","application/json")
            .addHeader("Accept", "application/json")
            .build()

        return chain.proceed(request)
    }
}