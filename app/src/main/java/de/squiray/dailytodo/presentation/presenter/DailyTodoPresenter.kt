package de.squiray.dailytodo.presentation.presenter

import de.squiray.dailytodo.domain.entity.Todo
import de.squiray.dailytodo.domain.entity.TodoType
import de.squiray.dailytodo.domain.usecase.NoOpResultHandler
import de.squiray.dailytodo.domain.usecase.todo.AddTodoUseCase
import de.squiray.dailytodo.domain.usecase.todo.GetTodosUseCase
import de.squiray.dailytodo.presentation.ui.view.DailyTodoView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DailyTodoPresenter @Inject
constructor(private val getTodosUseCase: GetTodosUseCase,
            private val addTodoUseCase: AddTodoUseCase)
    : Presenter<DailyTodoView>() {

    fun onAddTodoClicked(type: TodoType) {
        view.showAddTodoDialog(type)
    }

    fun onAddNewTodo(todo: String, type: TodoType) {
        addTodoUseCase.todo = todo
        addTodoUseCase.type = type
        addTodoUseCase.run(object : NoOpResultHandler<Todo>() {
            override fun onSuccess(todo: Todo) {
                view.showTodo(todo)
            }
        })
    }

    fun onLoadContent(type: TodoType) {
        getTodosUseCase.type = type
        getTodosUseCase.run(object : NoOpResultHandler<List<Todo>>() {
            override fun onSuccess(todos: List<Todo>) {
                view.showTodos(todos)
            }
        })

    }

}

