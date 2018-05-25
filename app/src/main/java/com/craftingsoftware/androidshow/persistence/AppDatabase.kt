package com.craftingsoftware.androidshow.persistence

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database

/**
 * Created by constantin.cheptea
 * on 22/05/2018.
 */
@Database(entities = [(User::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}