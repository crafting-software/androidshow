package com.craftingsoftware.androidshow.ui.users

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.craftingsoftware.androidshow.adapter.LocalUsersAdapter
import com.craftingsoftware.androidshow.R
import kotlinx.android.synthetic.main.fragment_local_users.*

/**
 * Created by constantin.cheptea
 * on 22/05/2018.
 */
class Users : Fragment() {

    private lateinit var viewModel: UsersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_local_users, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)

        usersRecycler.layoutManager = LinearLayoutManager(context)
        usersRecycler.setHasFixedSize(true)

        viewModel.getUsers().observe(this, Observer<List<User>> {
            usersRecycler.adapter = LocalUsersAdapter(it!!)
        })

        addUserButton.setOnClickListener {
            //TODO Open dialog to add new user
        }
    }
}