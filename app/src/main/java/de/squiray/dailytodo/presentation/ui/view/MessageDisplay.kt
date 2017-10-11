package de.squiray.dailytodo.presentation.ui.view

interface MessageDisplay {

    fun showMessage(messageId: Int, vararg args: Any)

    fun showMessage(message: String, vararg args: Any)

}
