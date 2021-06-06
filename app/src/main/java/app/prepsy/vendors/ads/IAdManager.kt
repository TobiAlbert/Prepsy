package app.prepsy.vendors.ads

import android.view.View

interface IAdManager {
    fun initialize()

    fun isInitialized(): Boolean

    fun loadAd(adView: View)
}