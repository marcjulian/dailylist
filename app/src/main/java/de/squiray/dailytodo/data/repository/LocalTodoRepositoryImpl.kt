package de.squiray.dailytodo.data.repository

import de.squiray.dailytodo.data.db.TodoDao
import de.squiray.dailytodo.data.entity.mapper.TodoEntityMapper
import de.squiray.dailytodo.domain.entity.Todo
import de.squiray.dailytodo.domain.repository.TodoRepository
import javax.inject.Inject


class LocalTodoRepositoryImpl @Inject
constructor(private val todoDao: TodoDao,
            private val todoEntityMapper: TodoEntityMapper) : TodoRepository {

    override val all: List<Todo>
        get() = todoEntityMapper.fromEntities(todoDao.all)

    override fun save(todo: Todo): Todo {
        todoDao.insertTodos(todoEntityMapper.toEntity(todo))
        return todo
    }

    override fun get(id: String): Todo {
        return todoEntityMapper.fromEntity(todoDao.get(id))
    }

    override fun deleteTodo(todo: Todo) {
        todoDao.deleteTodos(todoEntityMapper.toEntity(todo))
    }
}
