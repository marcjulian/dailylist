package de.squiray.dailytodo.presentation.ui.activity

import android.content.Intent
import android.support.design.widget.BottomNavigationView
import de.squiray.dailytodo.R
import de.squiray.dailytodo.domain.entity.Todo
import de.squiray.dailytodo.domain.entity.TodoType
import de.squiray.dailytodo.presentation.presenter.DailyTodoPresenter
import de.squiray.dailytodo.presentation.ui.bottomdialog.AddTodoBottomDialog
import de.squiray.dailytodo.presentation.ui.fragment.DailyTodoFragment
import de.squiray.dailytodo.presentation.ui.view.DailyTodoView
import de.squiray.dailytodo.util.annotation.Activity
import kotlinx.android.synthetic.main.layout_bottom_nav.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import javax.inject.Inject

@Activity(layout = R.layout.activity_daily_todo)
class DailyTodoActivity : BaseActivity(), DailyTodoView, AddTodoBottomDialog.Callback {

    @Inject
    lateinit var dailyTodoPresenter: DailyTodoPresenter

    override fun setupView() {
        setupToolbar()
        showDailyTodoFragmentFor(TodoType.DAILY_TO_DO)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun getMenuResource(): Int {
        return R.menu.menu_daily_todo
    }

    override fun onMenuItemSelected(itemId: Int): Boolean {
        when (itemId) {
            R.id.action_settings -> {
                dailyTodoPresenter.startIntent(Intent(this, SettingsActivity::class.java))
                return true
            }
        }
        return super.onMenuItemSelected(itemId)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun showDailyTodoFragmentFor(todoType: TodoType) {
        replaceFragment(R.id.fragmentContainer, DailyTodoFragment.newInstance(todoType))
    }

    override fun showTodos(todos: List<Todo>) {
        dailyTodoFragment().showTodos(todos)
    }

    override fun showTodo(todo: Todo) {
        dailyTodoFragment().showTodo(todo)
    }

    private fun dailyTodoFragment(): DailyTodoFragment {
        return getCurrentFragment(R.id.fragmentContainer) as DailyTodoFragment
    }

    override fun showAddTodoDialog(type: TodoType) {
        showDialog(AddTodoBottomDialog.newInstance(type))
    }

    override fun onAddTodoClicked(todo: String, type: TodoType) {
        dailyTodoPresenter.onAddNewTodo(todo, type)
    }

    override fun deleteTodo(completedTodo: Todo) {
        dailyTodoFragment().deleteTodo(completedTodo)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_daily_todo -> {
                showDailyTodoFragmentFor(TodoType.DAILY_TO_DO)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_fear -> {
                showDailyTodoFragmentFor(TodoType.FEAR)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_goal -> {
                showDailyTodoFragmentFor(TodoType.GOAL)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
