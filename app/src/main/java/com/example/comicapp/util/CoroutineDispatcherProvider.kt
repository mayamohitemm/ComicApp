package com.example.comicapp.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import javax.inject.Inject

data class CoroutineDispatcherProvider(
    val main: CoroutineDispatcher,
    val io: CoroutineDispatcher,
    val default: CoroutineDispatcher,
) {
    @Inject
    constructor() : this(
        Main,
        IO,
        Default
    )
}
