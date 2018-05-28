package com.craftingsoftware.androidshow.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by constantin.cheptea
 * on 22/05/2018.
 */
@Entity(tableName = "user")
data class User(
        @PrimaryKey(autoGenerate = true)
        var uid: Int = 0,

        @ColumnInfo(name = "first_name")
        var firstName: String? = null,

        @ColumnInfo(name = "last_name")
        var lastName: String? = null
)