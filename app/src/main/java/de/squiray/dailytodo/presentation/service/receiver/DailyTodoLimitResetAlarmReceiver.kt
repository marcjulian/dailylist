package de.squiray.dailytodo.presentation.service.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import de.squiray.dailytodo.util.helper.SharedPreferencesHelper
import timber.log.Timber

class DailyTodoLimitResetAlarmReceiver : BroadcastReceiver() {

    companion object {
        val REQUEST_CODE = 5678910
    }

    override fun onReceive(context: Context, intent: Intent) {
        Timber.tag("DailyTodoLimitResetAlarmReceiver").i("reset daily todo")
        SharedPreferencesHelper(context).resetDailyTodoAdded()
    }

}
