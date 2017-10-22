package de.squiray.dailylist.presentation.service.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import de.squiray.dailylist.util.helper.SharedPreferencesHelper
import timber.log.Timber

class DailyStrikeResetAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val sharedPreferencesHelper = SharedPreferencesHelper(context)
        Timber.tag("DailyStrikeResetAlarmReceiver").i("reset daily strike inc today")
        if (!sharedPreferencesHelper.hasDailyStrikeIncToday()) {
            sharedPreferencesHelper.resetDailyStrikeCount()
        }
        sharedPreferencesHelper.setDailyStrikeIncToday(false)
    }

}
