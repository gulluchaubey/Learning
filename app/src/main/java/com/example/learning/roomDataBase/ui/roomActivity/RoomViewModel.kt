package com.example.learning.roomDataBase.ui.roomActivity

import androidx.lifecycle.*
import com.example.learning.roomDataBase.room.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(private val roomRepository: RoomRepository) : ViewModel() {
    private var _userData = MutableLiveData<User>()
    val userData: LiveData<User>
        get() = _userData

    fun getUser() = viewModelScope.launch {
        roomRepository.getUser.collect {
            _userData.postValue(it)
        }
    }
}