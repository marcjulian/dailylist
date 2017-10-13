package de.squiray.dailytodo.domain.usecase.todo

import de.squiray.dailytodo.domain.entity.Todo
import de.squiray.dailytodo.domain.entity.TodoType
import de.squiray.dailytodo.domain.repository.TodoRepository
import de.squiray.dailytodo.domain.usecase.UseCase
import de.squiray.dailytodo.util.thread.PostExecutionThread
import de.squiray.dailytodo.util.thread.ThreadExecutor
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(postExecThread: PostExecutionThread,
                                          threadExec: ThreadExecutor,
                                          private val todoRepository: TodoRepository) :
        UseCase<List<Todo>>(postExecThread, threadExec) {

    lateinit var type: TodoType

    override fun execute(): List<Todo> {
        var allTodos : List<Todo> = todoRepository.all
        return allTodos.filter { todo -> todo.todoType == type }
    }

}
