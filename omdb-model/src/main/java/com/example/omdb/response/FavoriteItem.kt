package com.example.omdb.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

 @Entity(tableName = "favorite_items")
    data class FavoriteItem(
        @SerializedName("Title")
        val title: String,
        @SerializedName("Year")
        val year: String,
        @PrimaryKey
        @ColumnInfo(name = "imdb_id")
        val imdbID: String,
        @SerializedName("Type")
        val type: String,
        @SerializedName("Poster")
        val poster: String
    )
