package com.yesitlab.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class UserData (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val age: String
)