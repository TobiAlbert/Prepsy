package app.prepsy

import app.prepsy.utils.AppDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
class AppTestDispatcher : AppDispatcher {
    override val io: CoroutineDispatcher
        get() = UnconfinedTestDispatcher()
    override val main: CoroutineDispatcher
        get() = UnconfinedTestDispatcher()
}