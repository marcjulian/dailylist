package de.squiray.dailylist.presentation.ui.fragment

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.AndroidInjection
import timber.log.Timber

abstract class BaseFragment : Fragment() {

    private var created: Boolean = false
    private var onViewCreatedCalled: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycle("onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        logLifecycle("onCreateView")
        return inflater.inflate(fragmentLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logLifecycle("onViewCreated")
        onViewCreatedCalled = true
    }

    override fun onAttach(context: Context?) {
        AndroidInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logLifecycle("onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        logLifecycle("onStart")

        if (onViewCreatedCalled) {
            setupView()
        }

        if (!created) {
            loadContent()
        }

        created = true
        onViewCreatedCalled = false
    }

    override fun onStop() {
        super.onStop()
        logLifecycle("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycle("onDestroy")
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("onResume")
    }

    override fun onPause() {
        super.onPause()
        logLifecycle("onPause")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logLifecycle("onDestroyView")
    }


    private fun fragmentLayout(): Int {
        return javaClass
                .getAnnotation(de.squiray.dailylist.util.annotation.Fragment::class.java)
                .layout
    }

    private fun logLifecycle(method: String) {
        Timber.tag("FragmentLifecycle").v(method + ' ' + this)
    }

    /**
     * Setup view such as recycler view.
     */
    protected abstract fun setupView()

    /**
     * Override if content must be loaded and presented before interaction with the user
     */
    open fun loadContent() {
        // default empty
    }
}
