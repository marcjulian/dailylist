package de.squiray.dailytodo.presentation.presenter

import android.content.Intent
import de.squiray.dailytodo.presentation.ui.activity.DailyTodoActivity
import de.squiray.dailytodo.presentation.ui.view.SplashView
import javax.inject.Inject

class SplashPresenter @Inject constructor() : Presenter<SplashView>() {

    override fun resumed() {
        startIntent(Intent(context(), DailyTodoActivity::class.java))
        finish()
    }

}
