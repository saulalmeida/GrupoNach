package com.example.gruponach.Retrofit.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="Movie")
data class Movie (
    @PrimaryKey(autoGenerate = true)
    val Uid: Int = 0,
    @ColumnInfo(name = "Adultos") val adult: Boolean,
    @ColumnInfo(name = "Backdrop") val backdrop_path: String,
    @ColumnInfo(name = "Id") val id: Int,
    @ColumnInfo(name = "Original_Language") val original_language: String,
    @ColumnInfo(name = "Original_Title") val original_title: String,
    @ColumnInfo(name = "OverView") val overview: String,
    @ColumnInfo(name = "Popularity") val popularity: Double,
    @ColumnInfo(name = "Poster_Path") val poster_path: String,
    @ColumnInfo(name = "Release_Date") val release_date: String,
    @ColumnInfo(name = "Title") val title: String,
    @ColumnInfo(name = "Video") val video: Boolean,
    @ColumnInfo(name = "Vote_Average") val vote_average: Double,
    @ColumnInfo(name = "Vote_Count") val vote_count: Int
)