package de.squiray.dailylist.domain.usecase.todo

import de.squiray.dailylist.domain.entity.Todo
import de.squiray.dailylist.domain.entity.TodoType
import de.squiray.dailylist.domain.repository.TodoRepository
import de.squiray.dailylist.domain.usecase.UseCase
import de.squiray.dailylist.util.helper.SharedPreferencesHelper
import de.squiray.dailylist.util.thread.PostExecutionThread
import de.squiray.dailylist.util.thread.ThreadExecutor
import javax.inject.Inject

class DeleteTodoUseCase @Inject constructor(postExecutionThread: PostExecutionThread,
                                            threadExecutor: ThreadExecutor,
                                            private val todoRepository: TodoRepository,
                                            private val sharedPreferencesHelper: SharedPreferencesHelper)
    : UseCase<Todo>(postExecutionThread, threadExecutor) {

    private val TAG = DeleteTodoUseCase::class.java.simpleName

    lateinit var todo: Todo

    override fun execute(): Todo {
        try {
            return todoRepository.deleteTodo(todo)
        } finally {
            if (todo.todoType == TodoType.DAILY_TO_DO) {
                sharedPreferencesHelper.removedDailyTodo()
            }
        }
    }


    override fun tag(): String = TAG
}
