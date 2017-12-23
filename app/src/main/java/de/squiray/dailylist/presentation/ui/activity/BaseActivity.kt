package de.squiray.dailylist.presentation.ui.activity

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.DaggerAppCompatActivity
import de.squiray.dailylist.R
import de.squiray.dailylist.presentation.presenter.Presenter
import de.squiray.dailylist.presentation.ui.view.View
import de.squiray.dailylist.util.annotation.Activity
import de.squiray.dailylist.util.extension.toast
import java.lang.String.format
import javax.inject.Inject


abstract class BaseActivity : DaggerAppCompatActivity(), View {

    private val NO_MENU = -1
    private val ACTIVE_DIALOG = "activeDialog"

    private var currentDialog: DialogFragment? = null

    private var presenter: Presenter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentLayout())

        presenter = Activities.initializePresenter(this)

        setupView()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (presenter != null) {
            presenter!!.resume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (presenter != null) {
            presenter!!.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (presenter != null) {
            presenter!!.destroy()
        }
    }

    private fun contentLayout(): Int {
        val layout = javaClass.getAnnotation(Activity::class.java).layout
        return if (layout == -1) {
            R.layout.activity_layout
        } else {
            layout
        }
    }

    protected fun replaceFragment(containerView: Int, fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(containerView, fragment)
        fragmentTransaction.commit()
    }


   override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuResource = getMenuResource()
        if (menuResource != NO_MENU) {
            menuInflater.inflate(menuResource, menu)
            onCreatingOptionsMenu(menu)
            return true
        }
        return super.onCreateOptionsMenu(menu)
    }

    open fun onCreatingOptionsMenu(menu: Menu) {

    }

    open fun getMenuResource(): Int {
        return NO_MENU
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onMenuItemSelected(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    open fun onMenuItemSelected(itemId: Int): Boolean {
        return false
    }

    open fun setupView() {

    }

    override fun context(): Context = this

    fun getCurrentFragment(fragmentContainer: Int): Fragment =
            fragmentManager.findFragmentById(fragmentContainer)

    override fun showError(messageId: Int) = toast(getString(messageId))


    override fun showMessage(messageId: Int, vararg args: Any) = showMessage(getString(messageId), args)


    override fun showError(message: String) = toast(message)


    override fun activity(): android.app.Activity = this


    override fun showMessage(message: String, vararg args: Any) = toast(format(message, args))

    override fun showDialog(dialog: DialogFragment) {
        closeDialog()
        currentDialog = dialog
        dialog.show(supportFragmentManager, ACTIVE_DIALOG)
    }

    override fun closeDialog() {
        if (currentDialog != null) {
            currentDialog!!.dismissAllowingStateLoss()
            currentDialog = null
        }
    }
}
