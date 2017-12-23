package de.squiray.dailylist.presentation.ui.fragment

import android.app.Activity
import android.os.Build
import dagger.android.AndroidInjection
import dagger.android.DaggerFragment
import timber.log.Timber

abstract class DaggerBaseFragment : DaggerFragment() {

    override fun onAttach(activity: Activity?) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Timber.tag("DaggerBaseFragment").d("onAttach Activity used SDK" + Build.VERSION.SDK_INT)
            AndroidInjection.inject(this)
        }
        super.onAttach(activity)
    }
}
