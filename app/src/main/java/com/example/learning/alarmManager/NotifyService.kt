package com.example.learning.alarmManager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.learning.R
import java.lang.UnsupportedOperationException

class NotifyService: Service() {
    val NOTIFICATION_CHANNEL_ID = "10001"
    private val default_notification_channel_id = "default"
    override fun onBind(intent: Intent?): IBinder? {
        var notificationIntent : Intent = Intent(applicationContext,AlarmActivity::class.java)
        notificationIntent.putExtra("fromNotification",true)
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        var pendingIntent : PendingIntent = PendingIntent.getActivities(this,0,
            arrayOf(notificationIntent),0)
        var notificationManager : NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        var builder : NotificationCompat.Builder = NotificationCompat.Builder(applicationContext,default_notification_channel_id)
        builder.setContentTitle("My Notification")
        builder.setContentIntent(pendingIntent)
        builder.setContentText("Notification Listener Service Example")
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
        builder.setAutoCancel(true)
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            var importance :Int = NotificationManager.IMPORTANCE_HIGH
            var notificationChannel : NotificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance)
            builder.setChannelId( NOTIFICATION_CHANNEL_ID )
            assert(notificationManager != null)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        assert(notificationManager!=null)
        notificationManager.notify(1,builder.build())
        throw UnsupportedOperationException("Not yet implemented")
    }
}