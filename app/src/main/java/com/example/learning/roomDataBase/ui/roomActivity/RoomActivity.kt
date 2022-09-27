package com.example.learning.roomDataBase.ui.roomActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.learning.R
import com.example.learning.databinding.ActivityRoomBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRoomBinding
    private val roomViewModel : RoomViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_room)

        roomViewModel.getUser()

        roomViewModel.userData.observe(this) {
            binding.user = it
            Toast.makeText(this,it.toString(),Toast.LENGTH_LONG).show()
        }
    }
}