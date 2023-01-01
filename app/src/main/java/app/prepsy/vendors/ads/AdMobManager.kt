package app.prepsy.vendors.ads

import android.content.Context
import android.view.View
import com.google.android.gms.ads.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import kotlin.Exception

@ExperimentalCoroutinesApi
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

@ExperimentalCoroutinesApi
fun AdView.listenForUpdates(): Flow<AdEvent> {
    val context = this

    return callbackFlow {
        val flowOffer: (AdEvent) -> Unit = {
            try {
                trySend(it)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        val listener = object: AdListener() {
            override fun onAdClosed() {
                super.onAdClosed()
                flowOffer(AdEvent.AdClosed)
            }

            override fun onAdImpression() {
                super.onAdImpression()
                flowOffer(AdEvent.AdImpression)
            }

            override fun onAdClicked() {
                super.onAdClicked()
                flowOffer(AdEvent.AdClicked)
            }

            override fun onAdFailedToLoad(error: LoadAdError) {
                super.onAdFailedToLoad(error)
                // error consists of the following properties:
                // domain; errorCode; message; cause
                // all that information is available in the error's `toString()` method
                flowOffer(AdEvent.AdFailedToLoad(error.toString()))
            }

            override fun onAdOpened() {
                super.onAdOpened()
                flowOffer(AdEvent.AdOpened)
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                flowOffer(AdEvent.AdLoaded)
            }
        }

        context.adListener = listener

        awaitClose {}
    }
}