package de.squiray.dailytodo.presentation.ui.activity

import de.squiray.dailytodo.presentation.presenter.Presenter

class Activities {

    companion object {
        fun initializePresenter(activity: BaseActivity): Presenter<*> {
            if (activity is SplashActivity) {
                val splashPresenter = activity.splashPresenter
                splashPresenter.view = activity
                return splashPresenter
            } else if (activity is DailyTodoActivity) {
                val dailyTodoPresenter = activity.dailyTodoPresenter
                dailyTodoPresenter.view = activity
                return dailyTodoPresenter
            } else if (activity is SettingsActivity) {
                val settingsPresenter = activity.presenter
                settingsPresenter.view = activity
                return settingsPresenter
            }

            throw IllegalStateException("Failed to initialize presenter for " + activity.javaClass.name)
        }
    }


}
