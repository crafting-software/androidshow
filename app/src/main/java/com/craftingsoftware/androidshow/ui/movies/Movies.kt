package com.craftingsoftware.androidshow.ui.movies

import android.arch.lifecycle.Observer
import android.support.v4.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.craftingsoftware.androidshow.Adapter.MovieListAdapter
import com.craftingsoftware.androidshow.R
import com.craftingsoftware.androidshow.ui.home.Home
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * Created by constantin.cheptea
 * on 25/05/2018.
 */
class Movies : Fragment() {

    companion object {
        fun newInstance() = Home()
    }

    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        recycle_movies.layoutManager = LinearLayoutManager(context)

        viewModel.getMovies().observe(this, Observer<List<Movie>> {

            recycle_movies.adapter = MovieListAdapter(it)
        })
    }
}