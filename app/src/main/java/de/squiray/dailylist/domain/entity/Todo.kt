package de.squiray.dailylist.domain.entity

import java.util.*

data class Todo(
        var id: String,
        var todo: String,
        var todoType: TodoType,
        var completed: Boolean) {

    constructor(todo: String,
                todoType: TodoType)
            : this(UUID.randomUUID().toString(), todo, todoType, false)

}
