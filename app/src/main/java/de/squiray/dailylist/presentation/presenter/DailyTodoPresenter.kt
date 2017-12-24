package de.squiray.dailylist.presentation.presenter

import de.squiray.dailylist.R
import de.squiray.dailylist.domain.entity.Todo
import de.squiray.dailylist.domain.entity.TodoType
import de.squiray.dailylist.domain.usecase.NoOpResultHandler
import de.squiray.dailylist.domain.usecase.todo.*
import de.squiray.dailylist.presentation.ui.view.DailyTodoView
import de.squiray.dailylist.util.helper.SharedPreferencesHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DailyTodoPresenter @Inject
constructor(private val getTodosUseCase: GetTodosUseCase,
            private val addTodoUseCase: AddTodoUseCase,
            private val completeTodoUseCase: CompleteTodoUseCase,
            private val deleteTodoUseCase: DeleteTodoUseCase,
            private val updateTodoUseCase: UpdateTodoUseCase,
            private val sharedPreferencesHelper: SharedPreferencesHelper)
    : Presenter<DailyTodoView>() {

    fun onWindowFocusChanged(hasFocus: Boolean, todoType: TodoType) {
        if (hasFocus) {
            onLoadContent(todoType)
        }
    }

    fun onAddTodoClicked(type: TodoType) {
        if (type == TodoType.DAILY_TO_DO && hasDailyLimitReached()) {
            view.showMessage(R.string.screen_daily_todo_limit_reached)
            return
        }
        view.showAddTodoDialog(type)
    }

    private fun hasDailyLimitReached(): Boolean {
        return sharedPreferencesHelper.getAddedDailyTodoNumber() >= sharedPreferencesHelper.getDailyTodoLimit().limit
    }

    fun onAddNewTodo(todo: String, type: TodoType) {
        addTodoUseCase.todo = todo
        addTodoUseCase.type = type
        addTodoUseCase.run(object : NoOpResultHandler<Todo>() {

            override fun onSuccess(todo: Todo) {
                view.addOrUpdate(todo)
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

    fun onDeleteTodoClicked(todo: Todo) {
        deleteTodoUseCase.todo = todo
        deleteTodoUseCase.run(object : NoOpResultHandler<Todo>() {
            override fun onSuccess(todo: Todo) {
                view.deleteTodo(todo)
            }
        })
    }

    fun onSaveTodoClicked(todo: Todo, changedTodo: String) {
        todo.todo = changedTodo
        updateTodoUseCase.todo = todo
        updateTodoUseCase.run(object : NoOpResultHandler<Todo>() {
            override fun onSuccess(todo: Todo) {
                view.addOrUpdate(todo)
            }
        })
    }

    fun onTodoClicked(todo: Todo) {
        view.showChangeTodoDialog(todo)
    }
}

