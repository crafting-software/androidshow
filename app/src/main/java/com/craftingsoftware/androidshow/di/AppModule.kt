package com.craftingsoftware.androidshow.di

import android.app.Application
import android.arch.persistence.room.Room
import com.craftingsoftware.androidshow.BuildConfig
import com.craftingsoftware.androidshow.api.GithubApi
import com.craftingsoftware.androidshow.persistence.AppDatabase
import com.craftingsoftware.androidshow.persistence.UserDao
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import timber.log.Timber
import javax.inject.Singleton


/**
 * Created by constantin.cheptea
 * on 22/05/2018.
 */

@Module
class AppModule {
    private val appDatabaseName = "android.show.database"

    @Singleton
    @Provides
    fun appDatabase(application: Application) = Room
            .databaseBuilder(application, AppDatabase::class.java, appDatabaseName)
            .build()

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor { log -> Timber.d(log) }
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.GITHUB_API_BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun githubApi(retrofit: Retrofit): GithubApi = retrofit.create(GithubApi::class.java)

    @Provides
    @Singleton
    fun userDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()
}