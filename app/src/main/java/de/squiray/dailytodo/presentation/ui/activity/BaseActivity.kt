package de.squiray.dailytodo.presentation.ui.activity

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.squiray.dailytodo.R
import de.squiray.dailytodo.util.annotation.Activity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
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

    protected fun addFragment(containerView: Int, fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(containerView, fragment)
        fragmentTransaction.commit()
    }

    open fun setupView() {

    }
}
