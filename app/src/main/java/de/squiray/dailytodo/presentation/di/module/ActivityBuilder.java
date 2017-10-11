package de.squiray.dailytodo.presentation.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import de.squiray.dailytodo.presentation.ui.activity.DailyTodoActivity;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = { DailyTodoActivityModule.class})
    abstract DailyTodoActivity contributeDailyTodoActivityInjector();

}
