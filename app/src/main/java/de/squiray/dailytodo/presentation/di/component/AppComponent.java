package de.squiray.dailytodo.presentation.di.component;

import android.app.Application;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import de.squiray.dailytodo.DailyTodoApp;
import de.squiray.dailytodo.data.repository.RepositoryModule;
import de.squiray.dailytodo.presentation.di.module.ActivityBuilder;
import de.squiray.dailytodo.presentation.di.module.AppModule;
import de.squiray.dailytodo.presentation.di.module.FragmentBuilder;
import de.squiray.dailytodo.presentation.di.module.ThreadModule;

@Component(modules = {AndroidInjectionModule.class, AppModule.class,
        ThreadModule.class, RepositoryModule.class,
        ActivityBuilder.class, FragmentBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(DailyTodoApp dailyTodoApp);
}
