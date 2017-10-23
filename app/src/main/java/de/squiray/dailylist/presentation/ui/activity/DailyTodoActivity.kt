package de.squiray.dailylist.presentation.ui.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.design.widget.BottomNavigationView
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.widget.TextView
import de.squiray.dailylist.R
import de.squiray.dailylist.domain.entity.Todo
import de.squiray.dailylist.domain.entity.TodoType
import de.squiray.dailylist.presentation.presenter.DailyTodoPresenter
import de.squiray.dailylist.presentation.ui.bottomdialog.AddOrChangeTodoBottomDialog
import de.squiray.dailylist.presentation.ui.fragment.DailyTodoFragment
import de.squiray.dailylist.presentation.ui.view.DailyTodoView
import de.squiray.dailylist.util.Consumer
import de.squiray.dailylist.util.annotation.Activity
import de.squiray.dailylist.util.helper.SharedPreferencesHelper
import kotlinx.android.synthetic.main.layout_bottom_nav.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import javax.inject.Inject


@Activity(layout = R.layout.activity_daily_todo)
class DailyTodoActivity : BaseActivity(), DailyTodoView, AddOrChangeTodoBottomDialog.Callback {

    @Inject
    lateinit var dailyTodoPresenter: DailyTodoPresenter

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun setupView() {
        setupToolbar()
        showDailyTodoFragmentFor(TodoType.DAILY_TO_DO)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        sharedPreferencesHelper.addDailyStrikeListener(onDailyStrikeCountChanged)
    }

    override fun getMenuResource(): Int {
        return R.menu.menu_daily_todo
    }

    override fun onCreatingOptionsMenu(menu: Menu) {
        val strikeCountItem = menu.findItem(R.id.action_strike_count)
        strikeCountItem.icon = buildStrikeCounterDrawable(sharedPreferencesHelper.getDailyStrikeCount())
    }

    private fun buildStrikeCounterDrawable(counter: Int): Drawable {
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.action_bar_strike_count, null)

        val textView: TextView = view.findViewById(R.id.count)
        textView.text = counter.toString()

        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)

        view.isDrawingCacheEnabled = true
        view.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
        val bitmap = Bitmap.createBitmap(view.drawingCache)
        view.isDrawingCacheEnabled = false

        return BitmapDrawable(resources, bitmap)
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

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.findItem(R.id.action_strike).isVisible =
                dailyTodoFragment().todoType == TodoType.DAILY_TO_DO
        menu.findItem(R.id.action_strike_count).isVisible =
                dailyTodoFragment().todoType == TodoType.DAILY_TO_DO

        if (sharedPreferencesHelper.getDailyStrikeCount() > 0) {
            menu.findItem(R.id.action_strike).icon.setColorFilter(
                    ContextCompat.getColor(this, R.color.colorOrange),
                    PorterDuff.Mode.SRC_ATOP)
            menu.findItem(R.id.action_strike_count).icon.setColorFilter(
                    ContextCompat.getColor(this, R.color.colorOrange),
                    PorterDuff.Mode.SRC_ATOP)
        }

        return super.onPrepareOptionsMenu(menu)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun showDailyTodoFragmentFor(todoType: TodoType) {
        replaceFragment(R.id.fragmentContainer, DailyTodoFragment.newInstance(todoType))
        invalidateOptionsMenu()
    }

    override fun showTodos(todos: List<Todo>) {
        dailyTodoFragment().showTodos(todos)
    }

    override fun addOrUpdate(todo: Todo) {
        dailyTodoFragment().addOrUpdate(todo)
    }

    private fun dailyTodoFragment(): DailyTodoFragment {
        return getCurrentFragment(R.id.fragmentContainer) as DailyTodoFragment
    }

    override fun showAddTodoDialog(type: TodoType) {
        showDialog(AddOrChangeTodoBottomDialog.newInstance(type))
    }

    override fun showChangeTodoDialog(todo: Todo) {
        showDialog(AddOrChangeTodoBottomDialog.newInstance(todo))
    }

    override fun onAddTodoClicked(todo: String, type: TodoType) {
        dailyTodoPresenter.onAddNewTodo(todo, type)
    }

    override fun onDeleteTodoClicked(todo: Todo) {
        dailyTodoPresenter.onDeleteTodoClicked(todo)
    }

    override fun onSaveTodoClicked(todo: Todo, changeTodo: String) {
        dailyTodoPresenter.onSaveTodoClicked(todo, changeTodo)
    }

    override fun deleteTodo(completedTodo: Todo) {
        dailyTodoFragment().deleteTodo(completedTodo)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        dailyTodoFragment().todoType
        when (item.itemId) {
            R.id.navigation_daily_todo -> {
                if (dailyTodoFragment().todoType == TodoType.DAILY_TO_DO) {
                    return@OnNavigationItemSelectedListener false
                }
                showDailyTodoFragmentFor(TodoType.DAILY_TO_DO)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_fear -> {
                if (dailyTodoFragment().todoType == TodoType.FEAR) {
                    return@OnNavigationItemSelectedListener false
                }
                showDailyTodoFragmentFor(TodoType.FEAR)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_goal -> {
                if (dailyTodoFragment().todoType == TodoType.GOAL) {
                    return@OnNavigationItemSelectedListener false
                }
                showDailyTodoFragmentFor(TodoType.GOAL)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private val onDailyStrikeCountChanged = Consumer<Int> {
        invalidateOptionsMenu()
    }

}
