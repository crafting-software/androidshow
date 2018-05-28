package com.craftingsoftware.androidshow.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.craftingsoftware.androidshow.R
import com.craftingsoftware.androidshow.persistence.User

class LocalUsersAdapter(private val users: List<User>) : RecyclerView.Adapter<LocalUsersAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_local_user, parent, false)
            .let { UserViewHolder(it) }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]

        holder.userFirstName.text = user.firstName
        holder.userLastName.text = user.lastName
    }

    inner class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val userFirstName: TextView = view.findViewById(R.id.user_first_name)
        val userLastName: TextView = view.findViewById(R.id.user_last_name)
    }
}