package com.craftingsoftware.androidshow.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.craftingsoftware.androidshow.R
import com.craftingsoftware.androidshow.service.DateTimeService
import kotlinx.android.synthetic.main.fragment_home.*

class Home : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        localUsersButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_home_to_users, null))
        githubUsersButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_home_to_github_users, null))
        startTimeNotification.setOnClickListener {
            val startTimeIntent = Intent(context, DateTimeService::class.java)
            startTimeIntent.action = DateTimeService.START_TIME
            context?.startService(startTimeIntent)
        }
    }

}
