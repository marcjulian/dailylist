package de.squiray.dailytodo.presentation.presenter

import de.squiray.dailytodo.presentation.ui.view.View

abstract class Presenter<V : View> {

    lateinit var view: V

}
