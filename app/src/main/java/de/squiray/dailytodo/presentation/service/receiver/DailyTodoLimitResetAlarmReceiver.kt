package de.squiray.dailytodo.presentation.service.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import de.squiray.dailytodo.util.helper.SharedPreferencesHelper
import timber.log.Timber

class DailyTodoLimitResetAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Timber.tag("DailyTodoLimitResetAlarmReceiver").i("reset daily todo")
        SharedPreferencesHelper(context).resetDailyTodoAdded()
    }

}
