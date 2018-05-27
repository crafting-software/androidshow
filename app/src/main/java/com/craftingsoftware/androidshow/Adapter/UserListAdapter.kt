package com.craftingsoftware.androidshow.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.craftingsoftware.androidshow.R
import com.craftingsoftware.androidshow.ui.users.User

class UserListAdapter(
        private val processUser: List<User>?) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return processUser!!.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = processUser!!.elementAt(position)

        holder.userFirstName.text = currentUser.firstName
        holder.userLastName.text = currentUser.lastName
    }

    inner class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val userFirstName = view.findViewById<TextView>(R.id.user_first_name)
        val userLastName = view.findViewById<TextView>(R.id.user_last_name)
    }
}