package org.juansanz.kmpmovies

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform