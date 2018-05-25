package com.craftingsoftware.androidshow.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import com.craftingsoftware.androidshow.persistence.AppDatabase
import android.arch.persistence.room.Room


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
}