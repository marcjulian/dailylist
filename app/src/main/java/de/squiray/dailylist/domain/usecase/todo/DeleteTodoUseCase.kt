package de.squiray.dailylist.domain.usecase.todo

import de.squiray.dailylist.domain.entity.Todo
import de.squiray.dailylist.domain.repository.TodoRepository
import de.squiray.dailylist.domain.usecase.UseCase
import de.squiray.dailylist.util.thread.PostExecutionThread
import de.squiray.dailylist.util.thread.ThreadExecutor
import javax.inject.Inject

class DeleteTodoUseCase @Inject constructor(postExecutionThread: PostExecutionThread,
                                            threadExecutor: ThreadExecutor,
                                            private val todoRepository: TodoRepository)
    : UseCase<Todo>(postExecutionThread, threadExecutor) {

    private val TAG = DeleteTodoUseCase::class.java.name

    lateinit var todo: Todo

    override fun execute(): Todo {
        return todoRepository.deleteTodo(todo)
    }


    override fun tag(): String = TAG
}
