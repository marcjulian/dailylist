package de.squiray.dailytodo.data.entity.mapper

import de.squiray.dailytodo.data.entity.TodoEntity
import de.squiray.dailytodo.domain.entity.Todo
import javax.inject.Inject

class TodoEntityMapper @Inject constructor() : EntityMapper<TodoEntity, Todo>() {

    override fun toEntity(domainObject: Todo): TodoEntity {
        return TodoEntity(domainObject.id, domainObject.todo, domainObject.todoType, domainObject.completed)
    }

    override fun fromEntity(entity: TodoEntity): Todo {
        return Todo(entity.id, entity.todo, entity.todoType, entity.completed)
    }


}
