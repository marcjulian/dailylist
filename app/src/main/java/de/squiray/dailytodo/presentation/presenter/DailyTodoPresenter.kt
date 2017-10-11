package de.squiray.dailytodo.presentation.presenter

import de.squiray.dailytodo.domain.entity.Todo
import de.squiray.dailytodo.domain.entity.TodoType
import de.squiray.dailytodo.domain.usecase.NoOpResultHandler
import de.squiray.dailytodo.domain.usecase.todo.AddTodoUseCase
import de.squiray.dailytodo.presentation.ui.view.DailyTodoView
import javax.inject.Inject


// TODO add scope
class DailyTodoPresenter @Inject constructor(private val addTodoUseCase: AddTodoUseCase)
    : Presenter<DailyTodoView>() {

    fun onAddTodoClicked(todo: String,
                         type: TodoType) {
        addTodoUseCase.todo = todo
        addTodoUseCase.type = type
        addTodoUseCase.run(object : NoOpResultHandler<Todo>() {
            override fun onSuccess(t: Todo?) {
                TODO("return to view")
            }
        })
    }

}

