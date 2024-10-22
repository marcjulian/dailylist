package de.squiray.dailylist.domain.usecase.todo

import de.squiray.dailylist.domain.entity.Todo
import de.squiray.dailylist.domain.entity.TodoType
import de.squiray.dailylist.domain.repository.TodoRepository
import de.squiray.dailylist.domain.usecase.UseCase
import de.squiray.dailylist.util.helper.SharedPreferencesHelper
import de.squiray.dailylist.util.thread.PostExecutionThread
import de.squiray.dailylist.util.thread.ThreadExecutor
import javax.inject.Inject

class AddTodoUseCase @Inject constructor(
        postExecThread: PostExecutionThread,
        threadExec: ThreadExecutor,
        private val todoRepository: TodoRepository,
        private val sharedPreferencesHelper: SharedPreferencesHelper
) : UseCase<Todo>(postExecThread, threadExec) {

    private val TAG = AddTodoUseCase::class.java.simpleName

    lateinit var todo: String
    lateinit var type: TodoType

    override fun execute(): Todo {
        try {
            return todoRepository.save(Todo(todo = todo, todoType = type))
        } finally {
            if (type == TodoType.DAILY_TO_DO) {
                sharedPreferencesHelper.addedDailyTodo()
            }
        }
    }

    override fun tag(): String = TAG

}

