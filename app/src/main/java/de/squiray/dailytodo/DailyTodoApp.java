package de.squiray.dailytodo;

import android.app.Activity;
import android.app.Application;
import android.os.Build;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import de.squiray.dailytodo.presentation.di.component.DaggerAppComponent;
import de.squiray.dailytodo.util.logging.CrashLogging;
import de.squiray.dailytodo.util.logging.DebugLogger;
import de.squiray.dailytodo.util.logging.ReleaseLogger;
import timber.log.Timber;

public class DailyTodoApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
        setupLogging();
        logAppStats();
    }

    private void initDagger() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    private void setupLogging() {
        setupLoggingFramework();
        CrashLogging.setup();
    }

    private void setupLoggingFramework() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugLogger());
        }
        Timber.plant(new ReleaseLogger(this));
    }

    private void logAppStats() {
        Timber.tag("App").i("DailyTodo v%s (%d) started on android %s / API%d using a %s", //
                BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE, //
                Build.VERSION.RELEASE, Build.VERSION.SDK_INT, //
                Build.MODEL);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }
}
