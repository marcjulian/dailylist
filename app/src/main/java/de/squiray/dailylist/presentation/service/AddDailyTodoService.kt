package de.squiray.dailylist.presentation.service

import android.content.Intent
import android.support.v4.app.RemoteInput
import dagger.android.DaggerIntentService
import de.squiray.dailylist.domain.entity.Todo
import de.squiray.dailylist.domain.entity.TodoType
import de.squiray.dailylist.domain.usecase.NoOpResultHandler
import de.squiray.dailylist.domain.usecase.todo.AddTodoUseCase
import de.squiray.dailylist.presentation.notification.DailyTodoNotificationManager
import timber.log.Timber
import javax.inject.Inject


class AddDailyTodoService : DaggerIntentService("addDailyTodoService") {

    @Inject
    lateinit var dailyListNotificationManager: DailyTodoNotificationManager

    @Inject
    lateinit var addTodoUseCase: AddTodoUseCase

    override fun onHandleIntent(intent: Intent?) {
        val addedTodoContent = getTodoContentText(intent)
        if (addedTodoContent != null) {
            Timber.tag("AddDailyTodoService").d("added todo $addedTodoContent")
            addTodoUseCase.todo = addedTodoContent
            addTodoUseCase.type = TodoType.DAILY_TO_DO
            addTodoUseCase.run(object : NoOpResultHandler<Todo>() {
                override fun onSuccess(todo: Todo) {
                    dailyListNotificationManager.notifyCompleteDailyTodoNotification(todo)
                }
            })
        }
    }

    private fun getTodoContentText(intent: Intent?): String? {
        return RemoteInput.getResultsFromIntent(intent)?.getString(KEY_DAILY_TODO_TEXT)
    }

    companion object {
        val KEY_DAILY_TODO_TEXT = "dailyTodoText"
    }

}
