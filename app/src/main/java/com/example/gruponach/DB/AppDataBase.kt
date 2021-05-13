package com.example.gruponach.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gruponach.DB.DAO.MovieDao
import com.example.gruponach.Retrofit.Models.Movie
import java.util.concurrent.Executors

@Database(entities = [Movie::class], version = 2, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun movieDao():MovieDao

    companion object {
        private var INSTANCE: AppDataBase? = null
        private const val NUMBER_OF_THREADS = 1
        val dataBaseWriterExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        fun getDatabase(context: Context?): AppDataBase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context!!, AppDataBase::class.java, "DataBase")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .setQueryExecutor(dataBaseWriterExecutor)
                    .build()
            }
            return INSTANCE
        }
    }
}
