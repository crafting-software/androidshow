package com.craftingsoftware.androidshow.ui.movies

import android.arch.lifecycle.Observer
import android.support.v4.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.craftingsoftware.androidshow.R
import com.craftingsoftware.androidshow.ui.home.Home
import com.craftingsoftware.androidshow.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        viewModel.getMovies().observe(this, Observer<List<Movie>> {
            // new adapter with this list of movies
        })
    }
}