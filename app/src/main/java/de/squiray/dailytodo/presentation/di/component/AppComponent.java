package de.squiray.dailytodo.presentation.di.component;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import de.squiray.dailytodo.DailyTodoApp;
import de.squiray.dailytodo.presentation.di.module.ActivityBuilder;
import de.squiray.dailytodo.presentation.di.module.FragmentBuilder;

@Component(modules = {AndroidInjectionModule.class,
        ActivityBuilder.class,
        FragmentBuilder.class})
public interface AppComponent {

    void inject(DailyTodoApp dailyTodoApp);
}
