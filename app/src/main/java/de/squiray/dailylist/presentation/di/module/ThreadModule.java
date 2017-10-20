package de.squiray.dailylist.presentation.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.squiray.dailylist.util.thread.JobExecutor;
import de.squiray.dailylist.util.thread.PostExecutionThread;
import de.squiray.dailylist.util.thread.ThreadExecutor;
import de.squiray.dailylist.util.thread.UIThread;

@Module
public class ThreadModule {

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }
}
