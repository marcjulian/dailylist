package de.squiray.dailylist.presentation.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import de.squiray.dailylist.presentation.service.receiver.DailyStreakResetAlarmReceiver
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class DailyStreakResetAlarm @Inject constructor(context: Context) {

    private val alarmMgr: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private val alarmIntent: PendingIntent

    init {
        val intent = Intent(context, DailyStreakResetAlarmReceiver::class.java)
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
    }

    fun scheduleAlarm() {
        Timber.tag("DailyStreakResetAlarm").i("schedule alarm")
        alarmMgr.setRepeating(AlarmManager.RTC, resetTime().timeInMillis,
                AlarmManager.INTERVAL_DAY, alarmIntent)
    }

    private fun resetTime(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        return calendar
    }
}
