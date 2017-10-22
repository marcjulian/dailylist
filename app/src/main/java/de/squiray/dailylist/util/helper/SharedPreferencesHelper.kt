package de.squiray.dailylist.util.helper

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import de.squiray.dailylist.util.Consumer
import de.squiray.dailylist.util.DailyTodoLimit
import de.squiray.dailylist.util.extension.SharedPreferenceExtension.getValue
import de.squiray.dailylist.util.extension.SharedPreferenceExtension.setValue
import java.util.*
import javax.inject.Inject


class SharedPreferencesHelper @Inject constructor(context: Context) : SharedPreferences.OnSharedPreferenceChangeListener {

    private val DAILY_TODO_LIMIT = "dailyTodoLimit"
    private val ADDED_DAILY_TODO_NUMBER = "addedDailyTodoNumber"
    private val DAILY_STRIKE_COUNT = "dailyStrikeCount"
    private val DAILY_STRIKE_INC_TODAY = "dailyStrikeSetToday"

    private val sharedPreferences: SharedPreferences
            = PreferenceManager.getDefaultSharedPreferences(context)

    private val dailyStrikeChangedListeners = WeakHashMap<Consumer<Int>, Void>()


    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    fun addDailyStrikeListener(listener: Consumer<Int>) {
        dailyStrikeChangedListeners.put(listener, null)
        listener.accept(getDailyStrikeCount())
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

    fun incrementDailyStrikeCount() {
        sharedPreferences.setValue(DAILY_STRIKE_COUNT, getDailyStrikeCount().inc())
    }

    fun resetDailyStrikeCount() {
        sharedPreferences.setValue(DAILY_STRIKE_COUNT, 0)
    }

    fun getDailyStrikeCount(): Int {
        return sharedPreferences.getValue(DAILY_STRIKE_COUNT, 0)!!
    }

    fun setDailyStrikeIncToday(strikeIncToday: Boolean) {
        sharedPreferences.setValue(DAILY_STRIKE_INC_TODAY, strikeIncToday)
    }

    fun hasDailyStrikeIncToday(): Boolean {
        return sharedPreferences.getValue(DAILY_STRIKE_INC_TODAY, false)!!
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences, key: String) {
        if (key == DAILY_STRIKE_COUNT) {
            val dailyStrikeCount = getDailyStrikeCount()
            for (consumer in dailyStrikeChangedListeners.keys) {
                consumer.accept(dailyStrikeCount)
            }
        }
    }

}
