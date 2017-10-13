package de.squiray.dailytodo.domain.repository

import de.squiray.dailytodo.domain.entity.Todo

interface TodoRepository {

    val all: List<Todo>

    fun save(todo: Todo): Todo

    operator fun get(id: String): Todo

    fun deleteTodo(todo: Todo)
}
