package app.prepsy.log

import android.annotation.SuppressLint
import android.util.Log
import app.prepsy.BuildConfig
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class ProductionTree: Timber.Tree() {

    @SuppressLint("LogNotTimber")
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.INFO) {
            val tagPrime = tag ?: "UNKNOWN"
            Log.i(tagPrime, message)
        } else if (priority == Log.ERROR && !BuildConfig.DEBUG) {
            FirebaseCrashlytics.getInstance().log(message)
        }
    }
}