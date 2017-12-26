package de.squiray.dailylist.presentation.notification

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import de.squiray.dailylist.R
import de.squiray.dailylist.domain.entity.Todo
import de.squiray.dailylist.presentation.service.CompleteDailyTodoService
import de.squiray.dailylist.presentation.service.CompleteDailyTodoService.Companion.KEY_COMPLETE_TODO
import javax.inject.Inject

class CompleteDailyTodoNotification @Inject constructor(
        private val context: Context
) : DailyTodoNotification(context) {

    private var todo: Todo? = null

    override fun build(notificationBuilder: NotificationCompat.Builder): Notification {
        return notificationBuilder
                .setContentTitle(context.getString(R.string.notification_complete_daily_todo_title))
                .setContentText(todo!!.todo)
                .addAction(completeTodoAction())
                .build()
    }

    private fun completeTodoAction(): NotificationCompat.Action {
        return NotificationCompat.Action.Builder(
                R.drawable.ic_complete,
                context.getString(R.string.notification_complete_daily_todo_action),
                completeActionIntent()).build()
    }

    private fun completeActionIntent(): PendingIntent {
        val completeDailyTodoServiceIntent = Intent(context, CompleteDailyTodoService::class.java)
        completeDailyTodoServiceIntent.putExtra(KEY_COMPLETE_TODO, todo)
        return PendingIntent.getService( //
                context.applicationContext, //
                0, //
                completeDailyTodoServiceIntent,
                PendingIntent.FLAG_CANCEL_CURRENT)
    }

    fun withTodo(todo: Todo): CompleteDailyTodoNotification {
        this.todo = todo
        return this
    }

}
