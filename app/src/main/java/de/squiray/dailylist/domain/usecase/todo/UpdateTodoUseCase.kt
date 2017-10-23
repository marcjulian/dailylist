package de.squiray.dailylist.domain.usecase.todo

import de.squiray.dailylist.domain.entity.Todo
import de.squiray.dailylist.domain.repository.TodoRepository
import de.squiray.dailylist.domain.usecase.UseCase
import de.squiray.dailylist.util.thread.PostExecutionThread
import de.squiray.dailylist.util.thread.ThreadExecutor
import javax.inject.Inject

class UpdateTodoUseCase @Inject constructor(postExecutionThread: PostExecutionThread,
                                            threadExecutor: ThreadExecutor,
                                            private val todoRepository: TodoRepository)
    : UseCase<Todo>(postExecutionThread, threadExecutor) {

    lateinit var todo: Todo

    override fun execute(): Todo {
        return todoRepository.update(todo)
    }
}
