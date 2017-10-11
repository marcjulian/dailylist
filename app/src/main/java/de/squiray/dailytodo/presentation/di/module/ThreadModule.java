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

    // TODO de.squiray.dailytodo.presentation.di.component.AppComponent (unscoped) may not reference scoped bindings:
    // TODO maybe need to scope appcomponent
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
