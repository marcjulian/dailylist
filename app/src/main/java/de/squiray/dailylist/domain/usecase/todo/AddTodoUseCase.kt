package de.squiray.dailylist.domain.usecase.todo

import de.squiray.dailylist.domain.entity.Todo
import de.squiray.dailylist.domain.entity.TodoType
import de.squiray.dailylist.domain.repository.TodoRepository
import de.squiray.dailylist.domain.usecase.UseCase
import de.squiray.dailylist.util.thread.PostExecutionThread
import de.squiray.dailylist.util.thread.ThreadExecutor
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

