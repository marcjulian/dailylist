package de.squiray.dailytodo.presentation.ui.activity

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import de.squiray.dailytodo.R
import de.squiray.dailytodo.presentation.presenter.Presenter
import de.squiray.dailytodo.presentation.ui.view.View
import de.squiray.dailytodo.util.annotation.Activity
import java.lang.String.format
import javax.inject.Inject


abstract class BaseActivity : AppCompatActivity(), HasFragmentInjector, View {

    private val ACTIVE_DIALOG = "activeDialog"

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    private var currentDialog: DialogFragment? = null

    private var presenter: Presenter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(contentLayout())

        presenter = Activities.initializePresenter(this)

        setupView()
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

    override fun fragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    open fun setupView() {

    }

    override fun context(): Context {
        return this
    }

    fun getCurrentFragment(fragmentContainer: Int): Fragment {
        return fragmentManager.findFragmentById(fragmentContainer)
    }

    override fun showError(messageId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage(messageId: Int, vararg args: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun activity(): android.app.Activity {
        return this
    }

    override fun showMessage(message: String, vararg args: Any) {
        showToastMessage(format(message, args))
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

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
