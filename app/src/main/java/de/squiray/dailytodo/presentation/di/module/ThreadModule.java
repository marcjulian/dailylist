package de.squiray.dailytodo.presentation.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.squiray.dailytodo.util.thread.JobExecutor;
import de.squiray.dailytodo.util.thread.PostExecutionThread;
import de.squiray.dailytodo.util.thread.ThreadExecutor;
import de.squiray.dailytodo.util.thread.UIThread;

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
