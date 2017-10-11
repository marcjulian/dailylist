package de.squiray.dailytodo.data.repository;

import java.util.List;

import javax.inject.Inject;

import de.squiray.dailytodo.data.db.TodoDao;
import de.squiray.dailytodo.data.entity.mapper.TodoEntityMapper;
import de.squiray.dailytodo.domain.entity.Todo;
import de.squiray.dailytodo.domain.repository.TodoRepository;


public class LocalTodoRepositoryImpl implements TodoRepository {

    private final TodoDao todoDao;
    private final TodoEntityMapper todoEntityMapper;

    @Inject
    public LocalTodoRepositoryImpl(TodoDao todoDao,
                                   TodoEntityMapper todoEntityMapper) {
        this.todoDao = todoDao;
        this.todoEntityMapper = todoEntityMapper;
    }

    @Override
    public Todo save(Todo todo) {
        todoDao.insertTodos(todoEntityMapper.toEntity(todo));
        return todo;
    }

    @Override
    public Todo get(String todoId) {
        return todoEntityMapper.fromEntity(todoDao.get(todoId));
    }

    @Override
    public List<Todo> getAll() {
        return todoEntityMapper.fromEntities(todoDao.getAll());
    }

    @Override
    public void deleteTodo(Todo todo) {
        todoDao.deleteTodos(todoEntityMapper.toEntity(todo));
    }
}
