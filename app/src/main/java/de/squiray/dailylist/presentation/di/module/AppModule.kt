package de.squiray.dailylist.presentation.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.squiray.dailylist.presentation.service.AddDailyTodoService
import de.squiray.dailylist.presentation.service.CompleteDailyTodoService
import de.squiray.dailylist.presentation.service.DailyListService

@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context

    @ContributesAndroidInjector
    abstract fun contributeDailyListServiceInjector(): DailyListService

    @ContributesAndroidInjector
    abstract fun contributeAddDailyTodoService(): AddDailyTodoService

    @ContributesAndroidInjector
    abstract fun contributeCompleteDailyTodoService(): CompleteDailyTodoService
}
