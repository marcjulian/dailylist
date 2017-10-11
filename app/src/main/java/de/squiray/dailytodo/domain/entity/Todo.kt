package de.squiray.dailytodo.domain.entity

import java.util.*

data class Todo(
        var id: String,
        var todo: String,
        var todoType: TodoType) {

    constructor(todo: String,
                todoType: TodoType)
            : this(UUID.randomUUID().toString(), todo, todoType)
}
