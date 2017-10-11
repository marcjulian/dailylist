package de.squiray.dailytodo.domain.entity

data class Todo(
        var id: String,
        var todo: String,
        var todoType: TodoType)
