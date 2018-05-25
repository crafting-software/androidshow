package com.craftingsoftware.androidshow.ui.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.craftingsoftware.androidshow.R
import kotlinx.android.synthetic.main.fragment_home.*

class Home : Fragment() {

    companion object {
        fun newInstance() = Home()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        increment.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_home_to_users, null))

        val countObserver = Observer<Int> {
            message.text = it.toString()
        }

        viewModel.getCount().observe(this, countObserver)
    }

}
