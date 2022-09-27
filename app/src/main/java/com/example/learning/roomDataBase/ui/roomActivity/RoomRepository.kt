package com.example.learning.roomDataBase.ui.roomActivity

import com.example.learning.roomDataBase.room.User
import com.example.learning.roomDataBase.room.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoomRepository @Inject constructor(private val userDao: UserDao) {

    val getUser = userDao.getAllUser()

    fun insertUser() {
        CoroutineScope(Job() + Dispatchers.IO).launch {
            userDao.insertUser(User(0))
        }
    }
}
