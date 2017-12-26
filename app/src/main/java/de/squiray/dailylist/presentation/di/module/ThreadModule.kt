package de.squiray.dailylist.presentation.di.module

import dagger.Module
import dagger.Provides
import de.squiray.dailylist.util.thread.JobExecutor
import de.squiray.dailylist.util.thread.PostExecutionThread
import de.squiray.dailylist.util.thread.ThreadExecutor
import de.squiray.dailylist.util.thread.UIThread
import javax.inject.Singleton

@Module
class ThreadModule {

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }
}
