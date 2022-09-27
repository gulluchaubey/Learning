package com.example.learning.roomDataBase.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.learning.roomDataBase.ui.roomActivity.Converters

@Database(entities = [User::class], version = 1)
//@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}