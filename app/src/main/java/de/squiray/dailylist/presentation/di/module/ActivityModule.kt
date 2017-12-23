package de.squiray.dailylist.presentation.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.squiray.dailylist.presentation.ui.activity.DailyTodoActivity
import de.squiray.dailylist.presentation.ui.activity.SettingsActivity
import de.squiray.dailylist.presentation.ui.activity.SplashActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashActivityInjector(): SplashActivity

    @ContributesAndroidInjector(modules = arrayOf(DailyTodoActivityModule::class))
    abstract fun contributeDailyTodoActivityInjector(): DailyTodoActivity

    @ContributesAndroidInjector
    abstract fun contributeSettingsActivityInjector(): SettingsActivity
}
