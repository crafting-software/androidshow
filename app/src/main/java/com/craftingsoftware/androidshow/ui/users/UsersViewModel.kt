package com.craftingsoftware.androidshow.ui.users

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.craftingsoftware.androidshow.AndroidShowApplication
import com.craftingsoftware.androidshow.persistence.User
import com.craftingsoftware.androidshow.persistence.UserDao
import javax.inject.Inject

/**
 * Created by constantin.cheptea
 * on 22/05/2018.
 */
class UsersViewModel : ViewModel() {

    @Inject
    lateinit var userDao: UserDao

    private val users: MutableLiveData<List<User>> = MutableLiveData()

    init {
        AndroidShowApplication.diInjector().inject(this)
        users.value = listOf(
                User(firstName = "Ananas", lastName = "Curatatoru"),
                User(firstName = "Dino", lastName = "Tractoru")
        )
    }

    fun getUsers(): LiveData<List<User>> = userDao.getAll()

    fun saveUser(user: User) = userDao.insertAll(user)
}