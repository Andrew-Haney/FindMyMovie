package com.example.feature_favorite.util

import com.example.omdb.response.FavoriteItem

sealed class ViewState {

    object NoQuery : ViewState()
    object Loading : ViewState()
    data class FavoriteSuccess(val favoriteItems: List<FavoriteItem>): ViewState()

    sealed class Failure : ViewState() {
        data class WhileFetching(val exception: String) : Failure()
        object InvalidQuery : Failure()
        object NoMediaItemsFound : Failure()
        object SomethingWentWrong : Failure()
    }}