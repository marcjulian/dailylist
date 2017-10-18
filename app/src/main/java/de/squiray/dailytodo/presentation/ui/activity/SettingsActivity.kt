package de.squiray.dailytodo.presentation.ui.activity

import de.squiray.dailytodo.R
import de.squiray.dailytodo.presentation.presenter.SettingsPresenter
import de.squiray.dailytodo.presentation.ui.view.SettingsView
import de.squiray.dailytodo.util.annotation.Activity
import kotlinx.android.synthetic.main.layout_toolbar.*
import javax.inject.Inject

@Activity(layout = R.layout.activity_settings)
class SettingsActivity : BaseActivity(), SettingsView {

    @Inject
    lateinit var presenter: SettingsPresenter

    override fun setupView() {
       setupToolbar()
    }

    private fun setupToolbar() {
        toolbar.setTitle(R.string.screen_settings_title)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }
}
