package de.squiray.dailylist.presentation.ui.activity

import de.squiray.dailylist.R
import de.squiray.dailylist.presentation.presenter.SettingsPresenter
import de.squiray.dailylist.presentation.ui.view.SettingsView
import de.squiray.dailylist.util.annotation.Activity
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
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }
}
