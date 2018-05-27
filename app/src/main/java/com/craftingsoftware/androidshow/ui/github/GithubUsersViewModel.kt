package com.craftingsoftware.androidshow.ui.github

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.craftingsoftware.androidshow.AndroidShowApplication
import com.craftingsoftware.androidshow.api.GithubApi
import com.craftingsoftware.androidshow.api.model.GithubUser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by constantin.cheptea
 * on 25/05/2018.
 */
class GithubUsersViewModel : ViewModel() {

    @Inject
    lateinit var githubApi: GithubApi

    private val githubUsers: MutableLiveData<List<GithubUser>> = MutableLiveData()

    init {
        githubUsers.value = listOf()
        AndroidShowApplication.diInjector().inject(this)
    }

    fun loadMovies() {
        githubApi.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    githubUsers.value = it
                }, Timber::e)
    }

    fun getMovies(): LiveData<List<GithubUser>> = githubUsers
}