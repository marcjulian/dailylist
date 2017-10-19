package de.squiray.dailytodo.presentation.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import de.squiray.dailytodo.presentation.service.receiver.DailyTodoLimitResetAlarmReceiver
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class DailyTodoLimitResetAlarm @Inject constructor(context: Context) {

    private val alarmMgr: AlarmManager
    private val alarmIntent: PendingIntent

    init {
        alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DailyTodoLimitResetAlarmReceiver::class.java)
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
    }

    fun scheduleAlarm() {
        Timber.tag("DailyTodoLimitResetAlarm").i("schedule alarm")
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, resetTime().timeInMillis,
                AlarmManager.INTERVAL_DAY, alarmIntent)
    }

    // TODO get reset time from shared preference and add to settings time picker
    private fun resetTime(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, 20)
        return calendar
    }
}
