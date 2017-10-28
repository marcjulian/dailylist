package de.squiray.dailylist.presentation.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import de.squiray.dailylist.R
import de.squiray.dailylist.domain.entity.Todo
import de.squiray.dailylist.domain.entity.TodoType
import de.squiray.dailylist.presentation.presenter.DailyTodoPresenter
import de.squiray.dailylist.presentation.ui.adapter.TodoAdapter
import de.squiray.dailylist.util.annotation.Fragment
import kotlinx.android.synthetic.main.fragment_daily_todo.*
import javax.inject.Inject


@Fragment(layout = R.layout.fragment_daily_todo)
class DailyTodoFragment : BaseFragment() {

    companion object {

        private val ARG_TODO_TYPE = "todoType"

        fun newInstance(todoType: TodoType): DailyTodoFragment {
            val args = Bundle()
            args.putSerializable(ARG_TODO_TYPE, todoType)
            val fragment = DailyTodoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var dailyTodoPresenter: DailyTodoPresenter

    lateinit var todoAdapter: TodoAdapter

    val todoType: TodoType
        get() = arguments.getSerializable(ARG_TODO_TYPE) as TodoType

    override fun setupView() {
        setupToolbar()
        setupRecyclerView()
        add_new_todo.setOnClickListener(addNewTodoClickListener)
    }

    private fun setupRecyclerView() {
        todoAdapter = TodoAdapter(ArrayList(), todoAdapterCallback)
        rv_todos.layoutManager = LinearLayoutManager(activity)
        rv_todos.adapter = todoAdapter
    }

    override fun loadContent() {
        dailyTodoPresenter.onLoadContent(todoType)
    }

    private fun setupToolbar() {
        activity.title = getString(todoType.type)
    }

    private val addNewTodoClickListener = View.OnClickListener {
        dailyTodoPresenter.onAddTodoClicked(todoType)
    }

    fun showTodos(todos: List<Todo>) {
        todoAdapter.addAll(todos)
    }

    fun addOrUpdate(todo: Todo) {
        todoAdapter.addOrReplace(todo)
    }

    private val todoAdapterCallback = object : TodoAdapter.Callback {
        override fun onTodoClicked(todo: Todo) {
            dailyTodoPresenter.onTodoClicked(todo)
        }

        override fun onCompleteTodoClicked(todo: Todo) {
            dailyTodoPresenter.onCompleteTodoClicked(todo)
        }
    }

    fun deleteTodo(completedTodo: Todo) {
        todoAdapter.remove(completedTodo)
    }


}


