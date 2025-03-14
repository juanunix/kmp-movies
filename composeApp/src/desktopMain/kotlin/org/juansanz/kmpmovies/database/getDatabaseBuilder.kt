package org.juansanz.kmpmovies.database

import androidx.room.Room
import androidx.room.RoomDatabase
import org.juansanz.kmpmovies.data.database.MoviesDatabase
import java.io.File

fun getDatabaseBuilder(): RoomDatabase.Builder<MoviesDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "my_room.db")
    return Room.databaseBuilder<MoviesDatabase>(
        name = dbFile.absolutePath,
    )
}