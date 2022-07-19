package com.example.learning.alarmManager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learning.R
import java.util.*


class AlarmActivity : AppCompatActivity() {

    lateinit var btnSetAlarm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)


        title = "KotlinApp"
        btnSetAlarm = findViewById(R.id.btn)
        btnSetAlarm.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()

            var now : Calendar = Calendar.getInstance()
               /* calendar.set(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    0)*/
            calendar.set(2022,5,25,18,32,0)

            if(calendar.before(now))
                calendar.add(Calendar.DATE,1)
            setAlarm(calendar.timeInMillis)
        }

    }
    private fun setAlarm(timeInMillis: Long) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, MyAlarm::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_MUTABLE)
        alarmManager.setRepeating(
            AlarmManager.RTC,
            timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent)
        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show()
    }
    private class MyAlarm : BroadcastReceiver() {
        override fun onReceive(
            context: Context,
            intent: Intent) {
            Log.d("Alarm Bell", "Alarm just fired")
        }
    }
    fun createNotification(view: View?) {
        val myIntent = Intent(applicationContext, NotifyService::class.java)
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val pendingIntent = PendingIntent.getService(this, 0, myIntent, 0)
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MINUTE, 4)
        calendar.set(Calendar.HOUR_OF_DAY, 16)
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.getTimeInMillis(),
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }
}