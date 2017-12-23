package de.squiray.dailylist

import android.content.Intent
import android.os.Build
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import de.squiray.dailylist.presentation.di.component.DaggerAppComponent
import de.squiray.dailylist.presentation.service.DailyListService
import de.squiray.dailylist.util.logging.CrashLogging
import de.squiray.dailylist.util.logging.DebugLogger
import de.squiray.dailylist.util.logging.ReleaseLogger
import timber.log.Timber

class DailyListApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        setupLogging()
        logAppStats()
        startDailyListService()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
                .builder()
                .application(this)
                .build()
    }

    private fun startDailyListService() {
        startService(Intent(this, DailyListService::class.java))
    }

    private fun setupLogging() {
        setupLoggingFramework()
        CrashLogging.setup()
    }

    private fun setupLoggingFramework() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugLogger())
        }
        Timber.plant(ReleaseLogger(this))
    }

    private fun logAppStats() {
        Timber.tag("App").i("DailyTodo v%s (%d) started on android %s / API%d using a %s", //
                BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE, //
                Build.VERSION.RELEASE, Build.VERSION.SDK_INT, //
                Build.MODEL)
    }
}
