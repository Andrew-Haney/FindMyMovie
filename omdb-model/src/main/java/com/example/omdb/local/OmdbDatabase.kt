package com.example.omdb.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.omdb.local.dao.FavoritesDao
import com.example.omdb.local.dao.MediaItemDao
import com.example.omdb.response.FavoriteItem
import com.example.omdb.response.MediaItem

// Creates the database to be used for SQL operations
@Database(entities = [MediaItem::class, FavoriteItem::class], version = 1)
abstract class OmdbDatabase : RoomDatabase() {

    abstract fun mediaItemDao(): MediaItemDao
    abstract fun favoriteItemDao(): FavoritesDao

    companion object {
        // Sets the initial instance of the database to null so that
        // database is only ever initialized once.
        private var INSTANCE: OmdbDatabase? = null

        fun getInstance(
            // Creates instance of database if not already built
            app: Application
        ) = INSTANCE ?: Room.databaseBuilder(
            app, OmdbDatabase::class.java, "omdb-db"
        ).build().also { INSTANCE = it }
    }
}