package de.squiray.dailytodo.presentation.ui.activity

import de.squiray.dailytodo.presentation.presenter.Presenter

class Activities {

    companion object {
        fun initializePresenter(activity: BaseActivity): Presenter<*> {
            if (activity is DailyTodoActivity) {
                val dailyTodoPresenter = activity.dailyTodoPresenter
                dailyTodoPresenter.view = activity
                return dailyTodoPresenter
            }

            throw IllegalStateException("Failed to initialize presenter for " + activity.javaClass.name)
        }
    }


}
