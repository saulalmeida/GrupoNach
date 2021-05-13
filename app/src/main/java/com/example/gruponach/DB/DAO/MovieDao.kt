package com.example.gruponach.DB.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gruponach.Retrofit.Models.Movie

@Dao
interface MovieDao {
    @Insert
    fun insertAllMovies( pokemons: ArrayList<Movie>)

    @Query("SELECT * FROM Movie LIMIT 20")
    fun getMoviesOffline(): List<Movie>

    @Query("DELETE  FROM Movie ")
    fun deleteMoviesOffline()

}