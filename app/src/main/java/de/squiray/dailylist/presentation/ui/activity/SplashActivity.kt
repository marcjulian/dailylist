package de.squiray.dailylist.presentation.ui.activity

import de.squiray.dailylist.R
import de.squiray.dailylist.presentation.presenter.SplashPresenter
import de.squiray.dailylist.presentation.ui.view.SplashView
import de.squiray.dailylist.util.annotation.Activity
import javax.inject.Inject

@Activity(layout = R.layout.activity_splash)
class SplashActivity : BaseActivity(), SplashView {

    @Inject
    lateinit var splashPresenter: SplashPresenter
}
