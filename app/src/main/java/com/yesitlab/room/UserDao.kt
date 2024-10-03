package com.yesitlab.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface  UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserData)


    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<UserData>

    @Delete
    suspend fun deleteUser(user: UserData)

    // Add update functionality
    @Update
    suspend fun updateUser(user: UserData)
}