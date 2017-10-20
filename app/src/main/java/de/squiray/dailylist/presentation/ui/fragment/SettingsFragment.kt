package de.squiray.dailylist.presentation.ui.fragment

import android.os.Bundle
import android.preference.PreferenceFragment
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import de.squiray.dailylist.BuildConfig
import de.squiray.dailylist.R


class SettingsFragment : PreferenceFragment() {

    private val APP_VERSION_ITEM_KEY = "appVersion"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)
        setupAppVersion()
    }

    private fun setupAppVersion() {
        val preference = findPreference(APP_VERSION_ITEM_KEY)
        val versionName = SpannableString(BuildConfig.VERSION_NAME)
        versionName.setSpan( //
                ForegroundColorSpan(ContextCompat.getColor(activity, R.color.textLight)), //
                0, versionName.length, 0)
        preference.summary = versionName
    }
}
