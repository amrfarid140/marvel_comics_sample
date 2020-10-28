package me.amryousef.lib.domain

import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {
    fun io(): CoroutineContext
    fun main(): CoroutineContext
    fun background(): CoroutineContext
}
