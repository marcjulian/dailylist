package de.squiray.dailylist.presentation.notification

import android.app.Notification
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_CANCEL_CURRENT
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.RemoteInput
import de.squiray.dailylist.R
import de.squiray.dailylist.presentation.service.AddDailyTodoService
import javax.inject.Inject


class AddDailyTodoNotification @Inject constructor(
        private val context: Context
) : DailyTodoNotification(context) {

    override fun build(notificationBuilder: NotificationCompat.Builder): Notification {
        return notificationBuilder
                .setContentTitle(context.getString(R.string.notification_add_daily_todo_title))
                .setContentText(context.getString(R.string.notification_add_daily_todo_text))
                .addAction(directAddAction())
                .build()
    }

    private fun directAddAction(): NotificationCompat.Action {
        val addDailyTodoServiceIntent = Intent(context, AddDailyTodoService::class.java)
        addDailyTodoServiceIntent.putExtra(TAG, ID)
        val flags = FLAG_CANCEL_CURRENT
        val directReplyPendingIntent = PendingIntent.getService(context, 0, addDailyTodoServiceIntent, flags)
        val remoteInput = RemoteInput.Builder(AddDailyTodoService.KEY_DAILY_TODO_TEXT)
                .setLabel(context.getString(R.string.notification_add_daily_todo_input_text)).build()
        return NotificationCompat.Action.Builder(
                R.drawable.ic_add,
                context.getString(R.string.notification_add_daily_todo_action_text),
                directReplyPendingIntent)
                .addRemoteInput(remoteInput).build()
    }
}
