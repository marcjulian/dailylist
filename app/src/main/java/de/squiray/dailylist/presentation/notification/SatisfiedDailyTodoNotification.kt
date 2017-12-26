package de.squiray.dailylist.presentation.notification

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.support.v4.app.NotificationCompat
import de.squiray.dailylist.R
import de.squiray.dailylist.presentation.service.DailyListService
import javax.inject.Inject

class SatisfiedDailyTodoNotification @Inject constructor(
        private val context: Context
) : DailyTodoNotification(context) {

    override fun build(notificationBuilder: NotificationCompat.Builder): Notification {
        return notificationBuilder
                .setContentTitle(context.getString(R.string.notification_satisfied_daily_title))
                .setContentText(context.getString(R.string.notification_satisfied_daily_text))
                .addAction(doMoreAction())
                .addAction(satisfiedAction())
                .build()
    }

    private fun doMoreAction(): NotificationCompat.Action {
        return NotificationCompat.Action.Builder(
                R.drawable.ic_daily,
                context.getString(R.string.notification_satisfied_daily_do_more_action),
                doMoreActionIntent()).build()
    }

    private fun satisfiedAction(): NotificationCompat.Action {
        return NotificationCompat.Action.Builder(
                R.drawable.ic_daily,
                context.getString(R.string.notification_satisfied_daily_satisfied_action),
                satisfiedActionIntent()).build()
    }

    private fun doMoreActionIntent(): PendingIntent {
        return PendingIntent.getService( //
                context.applicationContext, //
                0, //
                DailyListService.doMoreDailyTodoNotificationIntent(context.applicationContext),
                PendingIntent.FLAG_CANCEL_CURRENT)
    }

    private fun satisfiedActionIntent(): PendingIntent {
        return PendingIntent.getService( //
                context.applicationContext, //
                0, //
                DailyListService.completedTodayNotificationIntent(context.applicationContext),
                PendingIntent.FLAG_CANCEL_CURRENT)
    }

}
