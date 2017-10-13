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
        // TODO open dialog on view
        addTodoUseCase.todo = "First Todo"
        addTodoUseCase.type = type
        addTodoUseCase.run(object : NoOpResultHandler<Todo>() {
            override fun onSuccess(t: Todo) {
                view.showMessage("Todo added")
            }
        })
    }

    fun onLoadContent(type: TodoType) {
        getTodosUseCase.type = type
        getTodosUseCase.run(object : NoOpResultHandler<List<Todo>>() {
            override fun onSuccess(todos: List<Todo>) {
                todos.forEach { todo -> view.showMessage(todo.todo) }
            }
        })

    }

}

