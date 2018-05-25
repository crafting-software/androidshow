package com.craftingsoftware.androidshow.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by constantin.cheptea
 * on 22/05/2018.
 */
@Entity
data class User(
        @PrimaryKey
        private val uid: Int = 0,

        @ColumnInfo(name = "first_name")
        private val firstName: String? = null,

        @ColumnInfo(name = "last_name")
        private val lastName: String? = null
)