package de.squiray.dailylist.domain.repository

import de.squiray.dailylist.domain.entity.Todo

interface TodoRepository {

    val all: List<Todo>

    fun save(todo: Todo): Todo

    fun update(todo: Todo): Todo

    operator fun get(id: String): Todo

    fun deleteTodo(todo: Todo): Todo
}
