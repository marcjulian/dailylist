package de.squiray.dailylist.presentation.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import de.squiray.dailylist.presentation.ui.fragment.DailyTodoFragment;

@Module
public abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract DailyTodoFragment contributeDailyTodoFragmentInjector();

}
