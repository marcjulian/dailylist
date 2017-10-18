package de.squiray.dailytodo.presentation.presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import de.squiray.dailytodo.presentation.ui.view.ActivityHolder
import de.squiray.dailytodo.presentation.ui.view.View


abstract class Presenter<V : View> : ActivityHolder {

    lateinit var view: V

    fun startIntent(intent: Intent) {
        activity().startActivity(intent)
    }

    override fun activity(): Activity = view.activity()

    override fun context(): Context = view.context()

}
