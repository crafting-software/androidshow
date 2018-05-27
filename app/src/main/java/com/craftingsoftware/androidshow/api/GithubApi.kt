package com.craftingsoftware.androidshow.api

import com.craftingsoftware.androidshow.api.model.GithubUser
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by constantin.cheptea
 * on 27/05/2018.
 */
interface GithubApi {

    @GET("users")
    fun getUsers(): Single<List<GithubUser>>

    @GET("users/{username}")
    fun getUser(@Path("username") username: String)

}