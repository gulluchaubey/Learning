package com.example.learning.roomDataBase.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAllUser(): Flow<User>

    @Insert
    fun insertUser(user:User)
}