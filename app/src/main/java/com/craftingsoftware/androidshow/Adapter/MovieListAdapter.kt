package com.craftingsoftware.androidshow.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.craftingsoftware.androidshow.R

class MovieListAdapter(
        private val proccesMovies: List<com.craftingsoftware.androidshow.ui.movies.Movie>?) : RecyclerView.Adapter<MovieListAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return proccesMovies!!.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val currentMovie = proccesMovies!!.elementAt(position)

        holder.title.text = currentMovie.title
        holder.director.text = currentMovie.director
        holder.year.text = currentMovie.year.toString()
    }

    inner class MoviesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.movie_title)
        val director = view.findViewById<TextView>(R.id.movie_director)
        val year = view.findViewById<TextView>(R.id.movie_year)
    }
}