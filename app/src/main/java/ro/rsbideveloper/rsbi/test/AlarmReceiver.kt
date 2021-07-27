package ro.rsbideveloper.rsbi.test

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ro.rsbideveloper.rsbi.R

class AlarmReceiver : BroadcastReceiver() {
    companion object {
        val requestCode = 0
        val flags = 0
        val channelId = "channelId"
        val notificationId = 123
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        // create Intent and PendingIntent
        val intentDestination = Intent(context, DestinationActivityForAlarm::class.java)
        intentDestination.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, requestCode, intentDestination, flags)

        // create Notification
        context?.let {
            val notification = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("App's alarm manager")
                .setContentText("Enter app using PendingIntent")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .build()

            // assign / notify into the NotificationManager
            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(notificationId, notification)
        }
    }
}