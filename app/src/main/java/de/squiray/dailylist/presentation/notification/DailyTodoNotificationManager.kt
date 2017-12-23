package de.squiray.dailylist.presentation.notification

import android.content.Context
import android.support.v4.app.NotificationManagerCompat
import javax.inject.Inject

class DailyTodoNotificationManager @Inject constructor(
        context: Context,
        private val dailyTodoNotification: DailyTodoNotification
) {

    private val notificationManager: NotificationManagerCompat
            = NotificationManagerCompat.from(context)

    fun notifyDailyTodoNotification(enable: Boolean) {
        if (enable) {
            notificationManager.notify(DailyTodoNotification.TAG,
                    DailyTodoNotification.ID,
                    dailyTodoNotification.notification(CHANNEL_ID))
        } else {
            notificationManager.cancel(DailyTodoNotification.TAG,
                    DailyTodoNotification.ID)
        }
    }

    companion object {
        private val CHANNEL_ID = "dailyListChannelId518239"
    }
}
