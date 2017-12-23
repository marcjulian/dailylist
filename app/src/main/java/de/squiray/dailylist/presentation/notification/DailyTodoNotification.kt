package de.squiray.dailylist.presentation.notification

import android.app.Notification
import android.content.Context
import android.support.v4.app.NotificationCompat
import de.squiray.dailylist.R
import javax.inject.Inject

class DailyTodoNotification @Inject constructor(
        private val context: Context
) {

    fun notification(channelId: String): Notification {
        return NotificationCompat.Builder(context, channelId)
                .setContentText("Daily Todo")
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_daily)
                .setShowWhen(false)
                .build()
    }

    companion object {
        val TAG = "dailyTodoNotification"
        val ID = 731939123
    }
}
