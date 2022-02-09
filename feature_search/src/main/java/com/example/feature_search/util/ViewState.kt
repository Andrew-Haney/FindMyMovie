package com.example.feature_search.util

import com.example.omdb.response.MediaItem

sealed class ViewState {
    object NoQuery : ViewState()
    object Loading : ViewState()
    data class Success(val mediaItems: List<MediaItem>) : ViewState()

    sealed class Failure : ViewState() {
        data class WhileFetching(val exception: String) : Failure()
        object InvalidQuery : Failure()
        object NoMediaItemsFound : Failure()
        object SomethingWentWrong : Failure()
    }
}
