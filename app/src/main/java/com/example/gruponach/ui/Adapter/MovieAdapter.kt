package com.example.gruponach.ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.gruponach.CommonUtils.Constantes
import com.example.gruponach.R
import com.example.gruponach.Retrofit.Models.Movie


class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var values :List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.tvTtile.text = item.title
        holder.tvRating.text = item.vote_average.toString()
        holder.ivFoto.load(Constantes.IMAGE_BASE_URL+item.poster_path){
            crossfade(true)
            placeholder(R.drawable.ic_movies)
            transformations(CircleCropTransformation())
        }

    }

    override fun getItemCount(): Int = values.size

    fun setData(popularMovies: List<Movie>?) {
        values = popularMovies!!
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTtile: TextView = view.findViewById(R.id.textViewTitle)
        val ivFoto: ImageView = view.findViewById(R.id.imageViewFoto)
        val tvRating: TextView = view.findViewById(R.id.textViewRating)

        override fun toString(): String {
            return super.toString() + " '" + tvTtile.text + "'"
        }
    }
}