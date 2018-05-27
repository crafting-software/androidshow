package com.craftingsoftware.androidshow.api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by constantin.cheptea
 * on 27/05/2018.
 */
data class GithubUser(
        @SerializedName("login") val username: String,
        @SerializedName("avatar_url") val avatarUrl: String
)