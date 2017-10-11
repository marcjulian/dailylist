package de.squiray.dailytodo.data.repository;

import java.util.List;

import de.squiray.dailytodo.data.db.TodoDao;
import de.squiray.dailytodo.domain.entity.Todo;
import de.squiray.dailytodo.domain.repository.TodoRepository;


public class LocalTodoRepositoryImpl implements TodoRepository {

    private final TodoDao todoDao;

    public LocalTodoRepositoryImpl(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    @Override
    public void save(Todo todo) {
        todoDao.insertTodos(todo);
    }

    @Override
    public Todo get(String todoId) {
        return todoDao.get(todoId);
    }

    @Override
    public List<Todo> getAll() {
        return todoDao.getAll();
    }

    @Override
    public void deleteTodo(Todo todo) {
        todoDao.deleteTodos(todo);
    }
}
