package app.prepsy.vendors.ads


sealed class AdEvent {
    object AdLoaded: AdEvent()
    object AdImpression: AdEvent()
    object AdClicked: AdEvent()
    object AdOpened: AdEvent()
    object AdClosed: AdEvent()
    data class AdFailedToLoad(val error: String): AdEvent()
}