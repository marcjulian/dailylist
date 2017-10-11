package de.squiray.dailytodo.presentation.ui.activity

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import de.squiray.dailytodo.R
import de.squiray.dailytodo.util.annotation.Activity
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(contentLayout())

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
}
