package de.squiray.dailytodo.presentation.di.component;

import android.app.Application;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import de.squiray.dailytodo.DailyTodoApp;
import de.squiray.dailytodo.presentation.di.module.ActivityBuilder;
import de.squiray.dailytodo.presentation.di.module.AppModule;
import de.squiray.dailytodo.presentation.di.module.FragmentBuilder;

@Component(modules = {AndroidInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class,
        FragmentBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(DailyTodoApp dailyTodoApp);
}
