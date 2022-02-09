package com.example.omdb.local.dao

import androidx.room.*
import com.example.omdb.response.FavoriteItem
import com.example.omdb.response.MediaItem
import kotlinx.coroutines.flow.Flow
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorite_items")
    fun getAll(): Flow<List<FavoriteItem>>

    @Insert
    suspend fun insertFavorite(favoriteItem: FavoriteItem)

    @Update
    suspend fun updateFavorite(favoriteItem: FavoriteItem)

    @Delete
    suspend fun delete(favoriteItem: FavoriteItem)
}