package de.squiray.dailylist.presentation.service

import android.content.Intent
import dagger.android.DaggerIntentService
import de.squiray.dailylist.domain.entity.Todo
import de.squiray.dailylist.domain.usecase.NoOpResultHandler
import de.squiray.dailylist.domain.usecase.todo.CompleteTodoUseCase
import de.squiray.dailylist.presentation.notification.DailyTodoNotificationManager
import javax.inject.Inject

class CompleteDailyTodoService : DaggerIntentService("completeDailyTodoService") {

    @Inject
    lateinit var dailyListNotificationManager: DailyTodoNotificationManager

    @Inject
    lateinit var completeTodoUseCase: CompleteTodoUseCase

    override fun onHandleIntent(intent: Intent?) {
        val completeTodo = getCompleteTodo(intent)
        if (completeTodo != null) {
            completeTodoUseCase.todo = completeTodo
            completeTodoUseCase.run(object : NoOpResultHandler<Todo>() {
                override fun onFinished() {
                    dailyListNotificationManager.notifySatisfiedDailyTodoNotification()
                }
            })
        }
    }

    private fun getCompleteTodo(intent: Intent?): Todo? {
        return intent?.getSerializableExtra(KEY_COMPLETE_TODO) as? Todo
    }

    companion object {
        val KEY_COMPLETE_TODO = "keyCompleteTodo"
    }
}
