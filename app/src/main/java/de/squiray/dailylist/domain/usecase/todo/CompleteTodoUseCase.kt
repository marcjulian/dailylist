package de.squiray.dailylist.domain.usecase.todo

import de.squiray.dailylist.domain.entity.Todo
import de.squiray.dailylist.domain.repository.TodoRepository
import de.squiray.dailylist.domain.usecase.UseCase
import de.squiray.dailylist.util.thread.PostExecutionThread
import de.squiray.dailylist.util.thread.ThreadExecutor
import javax.inject.Inject

class CompleteTodoUseCase @Inject constructor(postExecThread: PostExecutionThread,
                                              threadExec: ThreadExecutor,
                                              private val todoRepository: TodoRepository) :
        UseCase<Todo>(postExecThread, threadExec) {

    private val TAG = CompleteTodoUseCase::class.java.simpleName

    lateinit var todo: Todo

    override fun execute(): Todo {
        todo.completed = true
        return todoRepository.update(todo)
    }


    override fun tag(): String = TAG
}
