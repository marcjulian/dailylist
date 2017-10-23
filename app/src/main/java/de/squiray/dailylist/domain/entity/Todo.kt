package de.squiray.dailylist.domain.entity

import java.io.Serializable
import java.util.UUID

data class Todo(
        var id: String,
        var todo: String,
        var todoType: TodoType,
        var completed: Boolean) : Serializable {

    constructor(todo: String,
                todoType: TodoType)
            : this(UUID.randomUUID().toString(), todo, todoType, false)

}
