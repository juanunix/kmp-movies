package org.juansanz.kmpmovies

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.juansanz.kmpmovies.data.database.MoviesDao
import org.juansanz.kmpmovies.data.database.MoviesDatabase
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.juansanz.kmpmovies.ui.screens.home.HomeViewModel
import org.juansanz.kmpmovies.ui.screens.details.DetailViewModel
import org.juansanz.kmpmovies.data.MoviesRepository
import org.juansanz.kmpmovies.data.MoviesService

val appModule = module {
    single(named("apiKey")) { BuildConfig.API_KEY }
    single<MoviesDao> {
        val dbBuilder = get<RoomDatabase.Builder<MoviesDatabase>>()
        dbBuilder.setDriver(BundledSQLiteDriver()).build().moviesDao()
    }
}

val dataModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.themoviedb.org"
                    parameters.append("api_key", get(named("apiKey")))
                }
            }
        }
    }

    factoryOf(::MoviesRepository)
    factoryOf(::MoviesService)
}

val viewModelsModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}

expect val nativeModule: Module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModule, dataModule, viewModelsModule, nativeModule)
    }
}