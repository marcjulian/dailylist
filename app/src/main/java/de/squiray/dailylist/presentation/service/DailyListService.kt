package de.squiray.dailylist.presentation.service

import android.content.Context
import android.content.Intent
import android.os.IBinder
import dagger.android.DaggerService
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

    override fun onCreate() {
        super.onCreate()
        Timber.tag("DailyListService").d("onCreate")

        dailyTodoLimitResetAlarm.scheduleAlarm()
        dailyStreakResetAlarm.scheduleAlarm()

        sharedPreferencesHelper.addDailyTodoNotificationListener(dailyTodoNotificationConsumer)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.tag("DailyListService").d("onStartCommand")
        if (isShowNotificationIntent(intent)) {
            dailyListNotificationManager.notifyDailyTodoNotification(true)
            return START_STICKY
        } else if (isStopNotificationIntent(intent)) {
            dailyListNotificationManager.notifyDailyTodoNotification(false)
        }
        return START_NOT_STICKY
    }

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
    }
}
