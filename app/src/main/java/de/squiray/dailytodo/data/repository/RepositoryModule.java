package de.squiray.dailytodo.data.repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.squiray.dailytodo.domain.repository.TodoRepository;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    TodoRepository provideTodoRepository(LocalTodoRepositoryImpl localTodoRepository) {
        return localTodoRepository;
    }
}
