package de.squiray.dailylist.presentation.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import de.squiray.dailylist.DailylistApp;
import de.squiray.dailylist.data.repository.RepositoryModule;
import de.squiray.dailylist.presentation.di.module.ActivityBuilder;
import de.squiray.dailylist.presentation.di.module.AppModule;
import de.squiray.dailylist.presentation.di.module.FragmentBuilder;
import de.squiray.dailylist.presentation.di.module.ThreadModule;

@Singleton
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

    void inject(DailylistApp dailylistApp);
}
