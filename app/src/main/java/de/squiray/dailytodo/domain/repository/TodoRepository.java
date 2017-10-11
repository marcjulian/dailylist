package de.squiray.dailytodo.domain.repository;

import java.util.List;

import de.squiray.dailytodo.domain.entity.Todo;

public interface TodoRepository {

    void save(Todo todo);

    Todo get(String id);

    List<Todo> getAll();

    void deleteTodo(Todo todo);
}
