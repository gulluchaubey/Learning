package com.example.learning.roomDataBase.ui.roomActivity

import androidx.room.TypeConverter
import com.example.learning.roomDataBase.room.User
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun listToJson(value: List<User>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<User>::class.java).toList()

}