package de.squiray.dailylist.util.helper

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import de.squiray.dailylist.util.Consumer
import de.squiray.dailylist.util.DailyTodoLimit
import de.squiray.dailylist.util.extension.SharedPreferenceExtension.getValue
import de.squiray.dailylist.util.extension.SharedPreferenceExtension.setValue
import timber.log.Timber
import java.util.*
import javax.inject.Inject


class SharedPreferencesHelper @Inject constructor(
        context: Context
) : SharedPreferences.OnSharedPreferenceChangeListener {

    private val sharedPreferences: SharedPreferences
            = PreferenceManager.getDefaultSharedPreferences(context)

    private val dailyStreakChangedListeners = WeakHashMap<Consumer<Int>, Void>()
    private val dailyTodoNotificationChangedListeners = WeakHashMap<Consumer<Boolean>, Void>()

    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    fun addDailyTodoNotificationListener(listener: Consumer<Boolean>) {
        dailyTodoNotificationChangedListeners.put(listener, null)
        listener.accept(isDailyTodoNotificationEnabled())
    }

    fun addDailyStreakListener(listener: Consumer<Int>) {
        dailyStreakChangedListeners.put(listener, null)
        listener.accept(getDailyStreak())
    }

    fun getDailyTodoLimit(): DailyTodoLimit {
        return DailyTodoLimit.valueOf(
                sharedPreferences.getValue(DAILY_TODO_LIMIT, DailyTodoLimit.ONE_DAILY_TODO.name)!!)
    }

    fun addedDailyTodo() {
        sharedPreferences.setValue(ADDED_DAILY_TODO_NUMBER, getAddedDailyTodoNumber().inc())
    }

    fun removedDailyTodo() {
        if (getAddedDailyTodoNumber() > 0) {
            sharedPreferences.setValue(ADDED_DAILY_TODO_NUMBER, getAddedDailyTodoNumber().dec())
        }
    }

    fun resetDailyTodoAdded() {
        sharedPreferences.setValue(ADDED_DAILY_TODO_NUMBER, 0)
    }

    fun getAddedDailyTodoNumber(): Int {
        return sharedPreferences.getValue(ADDED_DAILY_TODO_NUMBER, 0)!!
    }

    fun updateDailyStreakCount() {
        if(!isDailyStreakIncrementedToday()) {
            incrementDailyStreakCount()
            setDailyStreakIncrementedToday(true)
        }
    }

    fun incrementDailyStreakCount() {
        sharedPreferences.setValue(DAILY_STREAK_COUNT, getDailyStreak().inc())
    }

    fun resetDailyStreakCount() {
        Timber.tag(TAG).d("reset daily streak count")
        sharedPreferences.setValue(DAILY_STREAK_COUNT, 0)
    }

    fun getDailyStreak(): Int {
        return sharedPreferences.getValue(DAILY_STREAK_COUNT, 0)!!
    }

    fun setDailyStreakIncrementedToday(isStreakIncremented: Boolean) {
        Timber.tag(TAG).d("set daily streak incremented today " + isStreakIncremented)
        sharedPreferences.setValue(DAILY_STREAK_INC_TODAY, isStreakIncremented)
    }

    fun isDailyStreakIncrementedToday(): Boolean {
        val isDailyStreakIncrementedToday = sharedPreferences.getValue(DAILY_STREAK_INC_TODAY, false)!!
        Timber.tag(TAG).d("daily streak is incremented today " + isDailyStreakIncrementedToday)
        return isDailyStreakIncrementedToday
    }

    fun isDailyTodoNotificationEnabled(): Boolean {
        return sharedPreferences.getValue(DAILY_TODO_NOTIFICATION, false)!!
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences, key: String) {
        if (key == DAILY_STREAK_COUNT) {
            val dailyStrikeCount = getDailyStreak()
            for (consumer in dailyStreakChangedListeners.keys) {
                consumer.accept(dailyStrikeCount)
            }
        } else if (key == DAILY_TODO_NOTIFICATION) {
            val isEnabled = isDailyTodoNotificationEnabled()
            for (consumer in dailyTodoNotificationChangedListeners.keys) {
                consumer.accept(isEnabled)
            }
        }
    }

    companion object {
        private val TAG = SharedPreferencesHelper::class.java.canonicalName
        private val DAILY_TODO_LIMIT = "dailyTodoLimit"
        private val ADDED_DAILY_TODO_NUMBER = "addedDailyTodoNumber"
        private val DAILY_STREAK_COUNT = "dailyStreakCount"
        private val DAILY_STREAK_INC_TODAY = "dailyStreakSetToday"
        private val DAILY_TODO_NOTIFICATION = "dailyTodoNotification"
    }
}
