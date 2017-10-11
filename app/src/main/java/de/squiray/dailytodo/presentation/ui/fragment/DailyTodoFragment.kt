package de.squiray.dailytodo.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import de.squiray.dailytodo.R
import de.squiray.dailytodo.domain.entity.TodoType
import de.squiray.dailytodo.presentation.presenter.DailyTodoPresenter
import de.squiray.dailytodo.util.annotation.Fragment
import kotlinx.android.synthetic.main.fragment_daily_todo.*
import javax.inject.Inject

@Fragment(layout = R.layout.fragment_daily_todo)
class DailyTodoFragment : BaseFragment() {

    @Inject
    lateinit var dailyTodoPresenter: DailyTodoPresenter

    private val todoType: TodoType
        get() = arguments.getSerializable(ARG_TODO_TYPE) as TodoType

    override fun setupView() {
        setupToolbar()
        setupRecyclerView()
        add_new_todo.setOnClickListener(addNewTodoClickListener)
    }

    private fun setupRecyclerView() {
        // TODO setup recyclerview and renderer
    }

    private fun setupToolbar() {
        activity.title = getString(todoType.type)
    }

    private val addNewTodoClickListener = View.OnClickListener { view ->

    }

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
}
