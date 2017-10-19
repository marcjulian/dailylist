package de.squiray.dailytodo.domain.usecase.todo

import de.squiray.dailytodo.domain.entity.Todo
import de.squiray.dailytodo.domain.repository.TodoRepository
import de.squiray.dailytodo.domain.usecase.UseCase
import de.squiray.dailytodo.util.thread.PostExecutionThread
import de.squiray.dailytodo.util.thread.ThreadExecutor
import javax.inject.Inject

class CompleteTodoUseCase @Inject constructor(postExecThread: PostExecutionThread,
                                              threadExec: ThreadExecutor,
                                              private val todoRepository: TodoRepository) :
        UseCase<Todo>(postExecThread, threadExec) {

    lateinit var todo: Todo

    override fun execute(): Todo {
        todo.completed = true
        return todoRepository.update(todo)
    }
}
