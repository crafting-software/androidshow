package com.craftingsoftware.androidshow.ui.users

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.craftingsoftware.androidshow.R
import com.craftingsoftware.androidshow.ui.home.Home

/**
 * Created by constantin.cheptea
 * on 22/05/2018.
 */
class Users : Fragment() {

    companion object {
        fun newInstance() = Home()
    }

    private lateinit var viewModel: UsersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_users, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)

        
    }
}