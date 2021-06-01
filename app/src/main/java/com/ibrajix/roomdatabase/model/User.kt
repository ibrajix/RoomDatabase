package com.ibrajix.roomdatabase.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class User(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    val id: Long? = null,
    val name: String? = null,
    val age: String? = null,
    val number: String? = null,
)