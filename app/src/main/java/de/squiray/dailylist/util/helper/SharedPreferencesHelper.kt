package de.squiray.dailylist.util.helper

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import de.squiray.dailylist.util.DailyTodoLimit
import de.squiray.dailylist.util.extension.SharedPreferenceExtension.getValue
import de.squiray.dailylist.util.extension.SharedPreferenceExtension.setValue
import javax.inject.Inject

class SharedPreferencesHelper @Inject constructor(context: Context) : SharedPreferences.OnSharedPreferenceChangeListener {

    private val DAILY_TODO_LIMIT = "dailyTodoLimit"
    private val ADDED_DAILY_TODO_NUMBER = "addedDailyTodoNumber"

    private val sharedPreferences: SharedPreferences
            = PreferenceManager.getDefaultSharedPreferences(context)

    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    fun getDailyTodoLimit(): DailyTodoLimit {
        return DailyTodoLimit.valueOf(
                sharedPreferences.getValue(DAILY_TODO_LIMIT, DailyTodoLimit.ONE_DAILY_TODO.name)!!)
    }

    fun incrementDailyTodoAdded() {
        sharedPreferences.setValue(ADDED_DAILY_TODO_NUMBER, getAddedDailyTodoNumber().inc())
    }

    fun resetDailyTodoAdded() {
        sharedPreferences.setValue(ADDED_DAILY_TODO_NUMBER, 0)
    }

    fun getAddedDailyTodoNumber(): Int {
        return sharedPreferences.getValue(ADDED_DAILY_TODO_NUMBER, 0)!!
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {

    }

}
