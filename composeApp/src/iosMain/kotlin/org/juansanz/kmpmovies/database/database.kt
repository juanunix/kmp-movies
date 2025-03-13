package org.juansanz.kmpmovies.database

import androidx.room.Room
import androidx.room.RoomDatabase
import org.juansanz.kmpmovies.data.database.DATABASE_NAME
import org.juansanz.kmpmovies.data.database.MoviesDatabase
import org.juansanz.kmpmovies.data.database.instantiateImpl
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilder(): RoomDatabase.Builder<MoviesDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$DATABASE_NAME"
    return Room.databaseBuilder<MoviesDatabase>(
        name = dbFilePath,
        factory =  { MoviesDatabase::class.instantiateImpl() }
    )
}