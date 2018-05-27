package com.craftingsoftware.androidshow.ui.users

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by constantin.cheptea
 * on 22/05/2018.
 */
class UsersViewModel : ViewModel() {

    private val users: MutableLiveData<List<User>> = MutableLiveData()

    init {
        users.value = listOf(
                User(firstName = "Ananas", lastName = "Curatatoru"),
                User(firstName = "Dino", lastName = "Tractoru")
        )
    }

    fun getUsers(): LiveData<List<User>> = users

    // Paul do the magic
}

data class User(
        val firstName: String,
        val lastName: String)