package de.squiray.dailylist.presentation.presenter

import de.squiray.dailylist.R
import de.squiray.dailylist.domain.entity.Todo
import de.squiray.dailylist.domain.entity.TodoType
import de.squiray.dailylist.domain.usecase.NoOpResultHandler
import de.squiray.dailylist.domain.usecase.todo.AddTodoUseCase
import de.squiray.dailylist.domain.usecase.todo.CompleteTodoUseCase
import de.squiray.dailylist.domain.usecase.todo.GetTodosUseCase
import de.squiray.dailylist.presentation.ui.view.DailyTodoView
import de.squiray.dailylist.util.helper.SharedPreferencesHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DailyTodoPresenter @Inject
constructor(private val getTodosUseCase: GetTodosUseCase,
            private val addTodoUseCase: AddTodoUseCase,
            private val completeTodoUseCase: CompleteTodoUseCase,
            private val sharedPreferencesHelper: SharedPreferencesHelper)
    : Presenter<DailyTodoView>() {

    fun onAddTodoClicked(type: TodoType) {
        if(type == TodoType.DAILY_TO_DO && hasDailyLimitReached()) {
            view.showMessage(R.string.screen_daily_todo_limit_reached)
            return
        }
        view.showAddTodoDialog(type)
    }

    private fun hasDailyLimitReached(): Boolean {
        return sharedPreferencesHelper.getAddedDailyTodoNumber() == sharedPreferencesHelper.getDailyTodoLimit().limit
    }

    fun onAddNewTodo(todo: String, type: TodoType) {
        addTodoUseCase.todo = todo
        addTodoUseCase.type = type
        addTodoUseCase.run(object : NoOpResultHandler<Todo>() {

            override fun onFinished() {
                if (type == TodoType.DAILY_TO_DO) {
                    sharedPreferencesHelper.incrementDailyTodoAdded()
                }
            }

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

    fun onCompleteTodoClicked(todo: Todo) {
        completeTodoUseCase.todo = todo
        completeTodoUseCase.run(object : NoOpResultHandler<Todo>() {
            override fun onSuccess(completedTodo: Todo) {
                view.deleteTodo(completedTodo)
            }
        })
    }

}

