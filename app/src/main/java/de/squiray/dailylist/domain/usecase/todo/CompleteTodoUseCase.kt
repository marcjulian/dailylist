package de.squiray.dailylist.domain.usecase.todo

import de.squiray.dailylist.domain.entity.Todo
import de.squiray.dailylist.domain.entity.TodoType
import de.squiray.dailylist.domain.repository.TodoRepository
import de.squiray.dailylist.domain.usecase.UseCase
import de.squiray.dailylist.util.helper.SharedPreferencesHelper
import de.squiray.dailylist.util.thread.PostExecutionThread
import de.squiray.dailylist.util.thread.ThreadExecutor
import javax.inject.Inject

class CompleteTodoUseCase @Inject constructor(postExecThread: PostExecutionThread,
                                              threadExec: ThreadExecutor,
                                              private val todoRepository: TodoRepository,
                                              private val sharedPreferencesHelper: SharedPreferencesHelper) :
        UseCase<Todo>(postExecThread, threadExec) {

    private val TAG = CompleteTodoUseCase::class.java.simpleName

    lateinit var todo: Todo

    override fun execute(): Todo {
        try {
            todo.completed = true
            return todoRepository.update(todo)
        } finally {
            if (todo.todoType == TodoType.DAILY_TO_DO) {
                sharedPreferencesHelper.updateDailyStreakCount()
            }
        }

    }


    override fun tag(): String = TAG
}
