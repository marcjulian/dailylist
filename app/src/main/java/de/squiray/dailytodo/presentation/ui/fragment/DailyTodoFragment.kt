package de.squiray.dailytodo.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import com.pedrogomez.renderers.RVRendererAdapter
import com.pedrogomez.renderers.Renderer
import com.pedrogomez.renderers.RendererBuilder
import de.squiray.dailytodo.R
import de.squiray.dailytodo.domain.entity.Todo
import de.squiray.dailytodo.domain.entity.TodoType
import de.squiray.dailytodo.presentation.presenter.DailyTodoPresenter
import de.squiray.dailytodo.presentation.ui.renderer.TodoRenderer
import de.squiray.dailytodo.util.annotation.Fragment
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

    lateinit var todoAdapter: RVRendererAdapter<Todo>

    private val todoType: TodoType
        get() = arguments.getSerializable(ARG_TODO_TYPE) as TodoType

    override fun setupView() {
        setupToolbar()
        setupRecyclerView()
        add_new_todo.setOnClickListener(addNewTodoClickListener)
    }

    private fun setupRecyclerView() {
        val todoRenderer: Renderer<Todo> = TodoRenderer()
        val rendererBuilder: RendererBuilder<Todo> = RendererBuilder(todoRenderer)
        todoAdapter = RVRendererAdapter<Todo>(rendererBuilder)
        rv_todos.adapter = todoAdapter
    }

    override fun loadContent() {
        dailyTodoPresenter.onLoadContent(todoType)
    }

    private fun setupToolbar() {
        activity.title = getString(todoType.type)
    }

    private val addNewTodoClickListener = View.OnClickListener { view ->
        dailyTodoPresenter.onAddTodoClicked(todoType)
    }

    fun showTodos(todos: List<Todo>) {
        todoAdapter.diffUpdate(todos)
    }

    fun showTodo(todo: Todo) {
        todoAdapter.add(todo)
        todoAdapter.notifyDataSetChanged()
    }

}
