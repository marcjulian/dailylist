package de.squiray.dailytodo.presentation.ui.view

import de.squiray.dailytodo.domain.entity.Todo
import de.squiray.dailytodo.domain.entity.TodoType

interface DailyTodoView : View {
    fun showTodos(todos: List<Todo>)
    fun showTodo(todo: Todo)
    fun showAddTodoDialog(type: TodoType)
}
