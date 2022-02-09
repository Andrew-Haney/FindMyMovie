package com.example.omdb.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("Search")
    val search: List<MediaItem>,
    val totalResults: String,
    @SerializedName("Response")
    val response: String,
    val favoriteResult: List<FavoriteItem>
)