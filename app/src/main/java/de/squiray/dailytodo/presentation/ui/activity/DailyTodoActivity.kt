package de.squiray.dailytodo.presentation.ui.activity

import android.support.design.widget.BottomNavigationView
import de.squiray.dailytodo.R
import de.squiray.dailytodo.domain.entity.TodoType
import de.squiray.dailytodo.presentation.ui.fragment.DailyTodoFragment
import de.squiray.dailytodo.util.annotation.Activity
import kotlinx.android.synthetic.main.layout_toolbar.*

@Activity(layout = R.layout.activity_daily_todo)
class DailyTodoActivity : BaseActivity() {

    override fun setupView() {
        setupToolbar()
        showDailyTodoFragmentFor(TodoType.DAILY_TO_DO)
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun showDailyTodoFragmentFor(todoType: TodoType) {
        addFragment(R.id.fragmentContainer, DailyTodoFragment.newInstance(todoType))
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_daily_todo -> {
                showDailyTodoFragmentFor(TodoType.DAILY_TO_DO)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_not_todo -> {
                showDailyTodoFragmentFor(TodoType.NOT_TO_DO)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_todo -> {
                showDailyTodoFragmentFor(TodoType.TO_DO)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}
