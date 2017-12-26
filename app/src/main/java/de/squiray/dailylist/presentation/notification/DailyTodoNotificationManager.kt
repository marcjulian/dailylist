package de.squiray.dailylist.presentation.notification

import android.content.Context
import android.support.v4.app.NotificationManagerCompat
import de.squiray.dailylist.domain.entity.Todo
import javax.inject.Inject

class DailyTodoNotificationManager @Inject constructor(
        context: Context,
        private val addDailyTodoNotification: AddDailyTodoNotification,
        private val completeDailyTodoNotification: CompleteDailyTodoNotification,
        private val satisfiedDailyTodoNotification: SatisfiedDailyTodoNotification,
        private val completedTodayNotification: CompletedTodayNotification
) {

    private val notificationManager: NotificationManagerCompat
            = NotificationManagerCompat.from(context)

    fun notifyAddDailyTodoNotification() {
        notificationManager.notify(DailyTodoNotification.TAG,
                DailyTodoNotification.ID,
                addDailyTodoNotification.notification(CHANNEL_ID))
    }

    fun notifyCompleteDailyTodoNotification(todo: Todo) {
        notificationManager.notify(DailyTodoNotification.TAG,
                DailyTodoNotification.ID,
                completeDailyTodoNotification.withTodo(todo).notification(CHANNEL_ID))
    }

    fun notifySatisfiedDailyTodoNotification() {
        notificationManager.notify(DailyTodoNotification.TAG,
                DailyTodoNotification.ID,
                satisfiedDailyTodoNotification.notification(CHANNEL_ID))
    }

    fun notifyCompletedTodayNotification() {
        notificationManager.notify(DailyTodoNotification.TAG,
                DailyTodoNotification.ID,
                completedTodayNotification.notification(CHANNEL_ID))
    }

    fun cancelDailyTodoNotification() {
        notificationManager.cancel(DailyTodoNotification.TAG,
                DailyTodoNotification.ID)
    }

    companion object {
        private val CHANNEL_ID = "dailyListChannelId518239"
    }

}
