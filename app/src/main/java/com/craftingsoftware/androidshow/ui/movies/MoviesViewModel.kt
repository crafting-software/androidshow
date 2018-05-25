package com.craftingsoftware.androidshow.ui.movies

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by constantin.cheptea
 * on 25/05/2018.
 */
class MoviesViewModel : ViewModel() {
    private val movies: MutableLiveData<List<Movie>> = MutableLiveData()

    init {
        movies.value = listOf(
                Movie(title = "Avatar", year = 2009, director = "James Cameron"),
                Movie(title = "Deadpool", year = 2015, director = "Marvel")
        )
    }

    fun loadMovies() {
        // get movies with Retrofit
    }

    fun getMovies(): LiveData<List<Movie>> = movies
}

data class Movie(
        val title: String,
        val year: Int,
        val director: String)