package me.amryousef.marvelcomics

import kotlinx.coroutines.Dispatchers
import me.amryousef.lib.domain.CoroutineContextProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidCoroutineContextProvider @Inject constructor() : CoroutineContextProvider {
    override fun io() = Dispatchers.IO

    override fun main() = Dispatchers.Main

    override fun background() = Dispatchers.Default
}
