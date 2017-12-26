package de.squiray.dailylist.presentation.notification

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import de.squiray.dailylist.R
import de.squiray.dailylist.presentation.ui.activity.DailyTodoActivity

abstract class DailyTodoNotification(
        private val context: Context
) {

    protected abstract fun build(notificationBuilder: NotificationCompat.Builder): Notification

    fun notification(channelId: String): Notification =
            build(prepareNotification(channelId))

    private fun prepareNotification(channelId: String): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_daily)
                .setOngoing(true)
                .setShowWhen(false)
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setContentIntent(startDailyTodoActivity())
    }

    private fun startDailyTodoActivity(): PendingIntent {
        val startTheActivity = Intent(context, DailyTodoActivity::class.java)
        startTheActivity.action = Intent.ACTION_MAIN
        startTheActivity.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        return PendingIntent.getActivity(context, 0, startTheActivity, 0)
    }

    companion object {
        val TAG = "dailyTodoNotification"
        val ID = 731939123
    }
}
