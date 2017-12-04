package de.squiray.dailylist.domain.usecase.todo

import de.squiray.dailylist.domain.entity.Todo
import de.squiray.dailylist.domain.entity.TodoType
import de.squiray.dailylist.domain.repository.TodoRepository
import de.squiray.dailylist.domain.usecase.UseCase
import de.squiray.dailylist.util.thread.PostExecutionThread
import de.squiray.dailylist.util.thread.ThreadExecutor
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(postExecThread: PostExecutionThread,
                                          threadExec: ThreadExecutor,
                                          private val todoRepository: TodoRepository) :
        UseCase<List<Todo>>(postExecThread, threadExec) {

    private val TAG = GetTodosUseCase::class.java.simpleName

    lateinit var type: TodoType

    override fun execute(): List<Todo> {
        val allTodos: List<Todo> = todoRepository.all
        return allTodos
                .asReversed()
                .filter { todo -> todo.todoType == type && !todo.completed }
    }


    override fun tag(): String = TAG
}
