package de.squiray.dailylist.presentation.ui.view

import de.squiray.dailylist.domain.entity.Todo
import de.squiray.dailylist.domain.entity.TodoType

interface DailyTodoView : View {
    fun showTodos(todos: List<Todo>)
    fun addOrUpdate(todo: Todo)
    fun showAddTodoDialog(type: TodoType)
    fun deleteTodo(completedTodo: Todo)
    fun showChangeTodoDialog(todo: Todo)
}
