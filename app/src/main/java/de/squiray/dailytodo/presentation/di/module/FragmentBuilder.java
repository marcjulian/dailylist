package de.squiray.dailytodo.presentation.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import de.squiray.dailytodo.presentation.ui.fragment.DailyTodoFragment;

@Module
public abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract DailyTodoFragment contributeDailyTodoFragmentInjector();

}
