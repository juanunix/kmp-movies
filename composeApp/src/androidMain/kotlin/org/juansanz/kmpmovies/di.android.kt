package org.juansanz.kmpmovies

import org.juansanz.kmpmovies.data.database.getDatabaseBuilder
import org.koin.dsl.module

actual val nativeModule = module {
    single { getDatabaseBuilder(get()) }
}