package app.prepsy.vendors.ads

import android.content.Context
import android.util.Log
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AdMobManager @Inject constructor(
    @ApplicationContext private val context: Context
) : IAdManager {

    private var isInitialized: Boolean = false

    init {
        initialize()
    }

    override fun initialize() {
        MobileAds.initialize(context) { _ ->
            isInitialized = true
        }
    }

    override fun isInitialized(): Boolean = isInitialized

    override fun loadAd(adView: View) {
        val adRequest = AdRequest.Builder().build()
        (adView as AdView).loadAd(adRequest)
    }

}