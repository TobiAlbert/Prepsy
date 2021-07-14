package app.prepsy.managers

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferenceManagers @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val SHARED_PREF_NAME = "app.prepsy.shared__prefs"
        const val HAS_SWIPED = "has_swiped"
        const val LAST_SELECTED_SUBJECT_ID = "last_selected_subject_id"
        const val LAST_SELECTED_YEAR_ID = "last_selected_year_id"
    }

    private fun getSharedPrefs(): SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    private fun getEditor(): SharedPreferences.Editor =
        getSharedPrefs().edit()

    fun saveBoolean(key: String, value: Boolean) {
        getEditor().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean =
        getSharedPrefs().getBoolean(key, defaultValue)

    fun saveString(key: String, value: String) {
        getEditor().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String = ""): String =
        getSharedPrefs().getString(key, defaultValue) ?: defaultValue
}