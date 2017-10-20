package de.squiray.dailytodo.presentation.ui.activity

import de.squiray.dailytodo.R
import de.squiray.dailytodo.presentation.presenter.SplashPresenter
import de.squiray.dailytodo.presentation.ui.view.SplashView
import de.squiray.dailytodo.util.annotation.Activity
import javax.inject.Inject

@Activity(layout = R.layout.activity_splash)
class SplashActivity : BaseActivity(), SplashView {

    @Inject
    lateinit var splashPresenter: SplashPresenter
}
