package de.squiray.dailytodo.data.repository;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.squiray.dailytodo.data.db.TodoDao;
import de.squiray.dailytodo.data.db.TodoDb;
import de.squiray.dailytodo.domain.repository.TodoRepository;

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
