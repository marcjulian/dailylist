package de.squiray.dailytodo;

import android.app.Application;
import android.os.Build;

import de.squiray.dailytodo.util.logging.CrashLogging;
import de.squiray.dailytodo.util.logging.DebugLogger;
import de.squiray.dailytodo.util.logging.ReleaseLogger;
import timber.log.Timber;

public class DailyTodoApp extends Application {

    // TODO add di with dagger

    @Override
    public void onCreate() {
        super.onCreate();
        setupLogging();
        Timber.tag("App").i("DailyTodo v%s (%d) started on android %s / API%d using a %s", //
                BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE, //
                Build.VERSION.RELEASE, Build.VERSION.SDK_INT, //
                Build.MODEL);
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
}
