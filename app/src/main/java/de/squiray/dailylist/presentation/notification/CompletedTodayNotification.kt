package de.squiray.dailylist.presentation.notification

import android.app.Notification
import android.content.Context
import android.support.v4.app.NotificationCompat
import de.squiray.dailylist.R
import javax.inject.Inject

class CompletedTodayNotification @Inject constructor(
        private val context: Context
) : DailyTodoNotification(context) {

    override fun build(notificationBuilder: NotificationCompat.Builder): Notification {
        return notificationBuilder
                // TODO show how many todos you completed today Completed (3)
                .setContentTitle(context.getString(R.string.notification_completed_today_title))
                .setContentText(context.getString(R.string.notification_completed_today_text))
                .build()
    }

}
