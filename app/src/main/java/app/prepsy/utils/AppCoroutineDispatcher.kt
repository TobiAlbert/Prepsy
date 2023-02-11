package app.prepsy.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface AppDispatcher {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
}

class AppCoroutineDispatcher @Inject constructor() : AppDispatcher {
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO

    override val main: CoroutineDispatcher
        get() = Dispatchers.Main.immediate
}
