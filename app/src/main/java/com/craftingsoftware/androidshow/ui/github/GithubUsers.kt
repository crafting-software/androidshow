package com.craftingsoftware.androidshow.ui.github

import android.arch.lifecycle.Observer
import android.support.v4.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.craftingsoftware.androidshow.adapter.GithubUsersAdapter
import com.craftingsoftware.androidshow.R
import com.craftingsoftware.androidshow.api.model.GithubUser
import com.craftingsoftware.androidshow.ui.home.Home
import kotlinx.android.synthetic.main.fragment_github_users.*

/**
 * Created by constantin.cheptea
 * on 25/05/2018.
 */
class GithubUsers : Fragment() {

    private lateinit var viewModel: GithubUsersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_github_users, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GithubUsersViewModel::class.java)
        usersRecycler.layoutManager = LinearLayoutManager(context)

        viewModel.getMovies().observe(this, Observer<List<GithubUser>> {
            usersRecycler.adapter = GithubUsersAdapter(it ?: emptyList())
        })

        viewModel.loadMovies()
    }
}