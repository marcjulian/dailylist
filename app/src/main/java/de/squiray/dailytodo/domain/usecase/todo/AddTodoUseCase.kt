package de.squiray.dailytodo.domain.usecase.todo

import de.squiray.dailytodo.domain.entity.Todo
import de.squiray.dailytodo.domain.entity.TodoType
import de.squiray.dailytodo.domain.repository.TodoRepository
import de.squiray.dailytodo.domain.usecase.UseCase
import de.squiray.dailytodo.util.thread.PostExecutionThread
import de.squiray.dailytodo.util.thread.ThreadExecutor
import javax.inject.Inject

class AddTodoUseCase @Inject constructor(postExecThread: PostExecutionThread,
                                         threadExec: ThreadExecutor,
                                         private val todoRepository: TodoRepository) :
        UseCase<Todo>(postExecThread, threadExec) {

    lateinit var todo: String
    lateinit var type: TodoType

    override fun execute(): Todo {
        return todoRepository.save(Todo(todo = todo, todoType = type))
    }
}

