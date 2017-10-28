package de.squiray.dailylist.presentation.service.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import de.squiray.dailylist.util.helper.SharedPreferencesHelper
import timber.log.Timber

class DailyStreakResetAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val sharedPreferencesHelper = SharedPreferencesHelper(context)
        Timber.tag("DailyStreakResetAlarmReceiver").i("reset daily streak inc today")
        if (!sharedPreferencesHelper.isDailyStreakIncrementedToday()) {
            sharedPreferencesHelper.resetDailyStreakCount()
        }
        sharedPreferencesHelper.setDailyStreakIncrementedToday(false)
    }

}
