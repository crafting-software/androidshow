package com.craftingsoftware.androidshow.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.craftingsoftware.androidshow.R
import com.craftingsoftware.androidshow.api.model.GithubUser

class GithubUsersAdapter(private val githubUsers: List<GithubUser>) : RecyclerView.Adapter<GithubUsersAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_github_user, parent, false)
            .let { MovieViewHolder(it) }

    override fun getItemCount(): Int = githubUsers.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.username.text = githubUsers[position].username
        Glide.with(holder.view.context)
                .load(githubUsers[position].avatarUrl)
                .into(holder.avatarView)
    }

    inner class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val username: TextView = view.findViewById(R.id.username)
        val avatarView: ImageView = view.findViewById(R.id.avatar)
    }
}