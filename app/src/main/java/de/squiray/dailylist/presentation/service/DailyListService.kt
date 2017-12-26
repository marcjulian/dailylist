package de.squiray.dailylist.presentation.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import dagger.android.DaggerService
import de.squiray.dailylist.domain.entity.Todo
import de.squiray.dailylist.domain.entity.TodoType
import de.squiray.dailylist.domain.usecase.NoOpResultHandler
import de.squiray.dailylist.domain.usecase.todo.GetTodosUseCase
import de.squiray.dailylist.presentation.notification.DailyTodoNotificationManager
import de.squiray.dailylist.util.Consumer
import de.squiray.dailylist.util.helper.SharedPreferencesHelper
import timber.log.Timber
import javax.inject.Inject

class DailyListService : DaggerService() {

    @Inject
    lateinit var dailyTodoLimitResetAlarm: DailyTodoLimitResetAlarm

    @Inject
    lateinit var dailyStreakResetAlarm: DailyStreakResetAlarm

    @Inject
    lateinit var dailyListNotificationManager: DailyTodoNotificationManager

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    @Inject
    lateinit var getTodosUseCase: GetTodosUseCase

    override fun onCreate() {
        super.onCreate()
        Timber.tag("DailyListService").d("onCreate")

        dailyTodoLimitResetAlarm.scheduleAlarm()
        dailyStreakResetAlarm.scheduleAlarm()

        sharedPreferencesHelper.addDailyTodoNotificationListener(dailyTodoNotificationConsumer)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.tag("DailyListService").d("onStartCommand")
        when {
            isShowNotificationIntent(intent) -> {
                Timber.tag("DailyListService").d("isShowNotificationIntent")
                handleShowNotification()
                return START_STICKY
            }
            isShowDoMoreDailyTodoNotification(intent) -> {
                Timber.tag("DailyListService").d("isShowDoMoreDailyTodoNotification")
                handleShowDoMoreNotification()
                return START_STICKY
            }
            isShowAddDailyTodoNotificationIntent(intent) -> {
                Timber.tag("DailyListService").d("isShowAddDailyTodoNotificationIntent")
                dailyListNotificationManager.notifyAddDailyTodoNotification()
                return START_STICKY
            }
            isShowCompletedTodayNotificationIntent(intent) -> {
                Timber.tag("DailyListService").d("isShowCompletedTodayNotificationIntent")
                dailyListNotificationManager.notifyCompletedTodayNotification()
                return Service.START_STICKY
            }
            isStopNotificationIntent(intent) -> dailyListNotificationManager.cancelDailyTodoNotification()
        }
        return START_NOT_STICKY
    }

    private fun handleShowDoMoreNotification() {
        getTodosUseCase.type = TodoType.DAILY_TO_DO
        getTodosUseCase.run(object : NoOpResultHandler<List<Todo>>() {
            override fun onSuccess(todos: List<Todo>) {
                showDoMoreNotificationFor(todos)
            }
        })
    }

    private fun showDoMoreNotificationFor(todos: List<Todo>) {
        if (todos.isEmpty()) {
            dailyListNotificationManager.notifyAddDailyTodoNotification()
        } else {
            dailyListNotificationManager.notifyCompleteDailyTodoNotification(todos[0])
        }
    }

    private fun handleShowNotification() {
        getTodosUseCase.type = TodoType.DAILY_TO_DO
        getTodosUseCase.run(object : NoOpResultHandler<List<Todo>>() {
            override fun onSuccess(todos: List<Todo>) {
                showNotificationFor(todos)
            }
        })
    }

    private fun showNotificationFor(todos: List<Todo>) {
        if (todos.isEmpty()) {
            if (sharedPreferencesHelper.hasDailyTodoLimitExceeded()) {
                dailyListNotificationManager.notifyCompletedTodayNotification()
            } else {
                dailyListNotificationManager.notifyAddDailyTodoNotification()
            }
        } else {
            dailyListNotificationManager.notifyCompleteDailyTodoNotification(todos[0])
        }
    }

    private fun isShowDoMoreDailyTodoNotification(intent: Intent?): Boolean =
            intent != null && intent.action == ACTION_SHOW_DO_MORE_DAILY_TODO_NOTIFICATION

    private fun isShowAddDailyTodoNotificationIntent(intent: Intent?): Boolean =
            intent != null && intent.action == ACTION_SHOW_ADD_DAILY_TODO_NOTIFICATION

    private fun isShowCompletedTodayNotificationIntent(intent: Intent?): Boolean =
            intent != null && intent.action == ACTION_SHOW_COMPLETED_TODAY_NOTIFICATION

    private fun isShowNotificationIntent(intent: Intent?): Boolean =
            intent != null && intent.action == ACTION_SHOW_DAILY_TODO_NOTIFICATION

    private fun isStopNotificationIntent(intent: Intent?): Boolean =
            intent != null && intent.action == ACTION_STOP_DAILY_TODO_NOTIFICATION

    override fun onBind(intent: Intent?): IBinder? = null

    private val dailyTodoNotificationConsumer = Consumer<Boolean> { isEnabled ->
        if (isEnabled) {
            startService(showDailyTodoNotificationIntent(this))
        } else {
            startService(stopDailyTodoNotificationIntent(this))
        }
    }

    companion object {
        private val ACTION_SHOW_DAILY_TODO_NOTIFICATION = "actionShowDailyTodoNotification"
        private val ACTION_STOP_DAILY_TODO_NOTIFICATION = "actionStopDailyTodoNotification"
        private val ACTION_SHOW_COMPLETED_TODAY_NOTIFICATION = "actionShowCompletedTodayNotification"
        private val ACTION_SHOW_ADD_DAILY_TODO_NOTIFICATION = "actionShowAddDailyTodoNotification"
        private val ACTION_SHOW_DO_MORE_DAILY_TODO_NOTIFICATION = "actionShowDoMoreDailyTodoNotification"

        fun showDailyTodoNotificationIntent(context: Context): Intent {
            val showNotificationIntent = Intent(context, DailyListService::class.java)
            showNotificationIntent.action = ACTION_SHOW_DAILY_TODO_NOTIFICATION
            return showNotificationIntent
        }

        fun stopDailyTodoNotificationIntent(context: Context): Intent {
            val stopNotificationIntent = Intent(context, DailyListService::class.java)
            stopNotificationIntent.action = ACTION_STOP_DAILY_TODO_NOTIFICATION
            return stopNotificationIntent
        }

        fun completedTodayNotificationIntent(context: Context): Intent {
            val completedTodayNotificationIntent = Intent(context, DailyListService::class.java)
            completedTodayNotificationIntent.action = ACTION_SHOW_COMPLETED_TODAY_NOTIFICATION
            return completedTodayNotificationIntent
        }

        fun doMoreDailyTodoNotificationIntent(context: Context): Intent {
            val doMoreNotificationIntent = Intent(context, DailyListService::class.java)
            doMoreNotificationIntent.action = ACTION_SHOW_DO_MORE_DAILY_TODO_NOTIFICATION
            return doMoreNotificationIntent
        }
    }
}
