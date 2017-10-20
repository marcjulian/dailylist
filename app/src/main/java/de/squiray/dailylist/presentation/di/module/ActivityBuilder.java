package de.squiray.dailylist.presentation.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import de.squiray.dailylist.presentation.ui.activity.DailyTodoActivity;
import de.squiray.dailylist.presentation.ui.activity.SettingsActivity;
import de.squiray.dailylist.presentation.ui.activity.SplashActivity;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract SplashActivity contributeSplashActivityInjector();

    @ContributesAndroidInjector(modules = { DailyTodoActivityModule.class})
    abstract DailyTodoActivity contributeDailyTodoActivityInjector();

    @ContributesAndroidInjector
    abstract SettingsActivity contributeSettingsActivityInjector();
}
