package com.example.gruponach.Retrofit.Models

data class PopularMovies(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)