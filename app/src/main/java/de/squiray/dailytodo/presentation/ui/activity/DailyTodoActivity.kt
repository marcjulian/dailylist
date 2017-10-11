package de.squiray.dailytodo.presentation.ui.activity

import android.support.design.widget.BottomNavigationView
import de.squiray.dailytodo.R
import de.squiray.dailytodo.domain.entity.TodoType
import de.squiray.dailytodo.presentation.presenter.DailyTodoPresenter
import de.squiray.dailytodo.presentation.ui.fragment.DailyTodoFragment
import de.squiray.dailytodo.presentation.ui.view.DailyTodoView
import de.squiray.dailytodo.util.annotation.Activity
import kotlinx.android.synthetic.main.layout_bottom_nav.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import javax.inject.Inject

@Activity(layout = R.layout.activity_daily_todo)
class DailyTodoActivity : BaseActivity(), DailyTodoView {

    @Inject
    lateinit var dailyTodoPresenter: DailyTodoPresenter

    override fun setupView() {
        setupToolbar()
        showDailyTodoFragmentFor(TodoType.DAILY_TO_DO)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun showDailyTodoFragmentFor(todoType: TodoType) {
        replaceFragment(R.id.fragmentContainer, DailyTodoFragment.newInstance(todoType))
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
            R.id.navigation_passion -> {
                showDailyTodoFragmentFor(TodoType.PASSION)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
