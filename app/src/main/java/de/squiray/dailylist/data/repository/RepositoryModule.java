package de.squiray.dailylist.data.repository;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.squiray.dailylist.data.db.TodoDao;
import de.squiray.dailylist.data.db.TodoDb;
import de.squiray.dailylist.domain.repository.TodoRepository;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    TodoRepository provideTodoRepository(LocalTodoRepositoryImpl localTodoRepository) {
        return localTodoRepository;
    }

    @Singleton
    @Provides
    TodoDao provideTodoDao(Context context) {
        TodoDb todoDb = TodoDb.Companion.getInstance(context);
        return todoDb.todoDao();
    }
}
