package com.example.feature_favorite.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.feature_favorite.util.ViewState
import com.example.omdb.OmdbRepo
import com.example.omdb.response.FavoriteItem
import com.example.omdb.response.SearchResponse
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class FavoriteViewModel(app: Application): AndroidViewModel(app) {

    private val omdbRepo by lazy { OmdbRepo(getApplication()) }

    private val _favoriteViewState = MutableLiveData<ViewState>(ViewState.NoQuery)
    val favoriteViewState: LiveData<ViewState> get() = _favoriteViewState

    init {
        viewModelScope.launch {
            omdbRepo.favoriteItems.firstOrNull()?.let { favoriteItems ->
                _favoriteViewState.value = ViewState.FavoriteSuccess(favoriteItems)
            }
        }
    }

    private fun addFavorites(query: List<FavoriteItem>) {
        _favoriteViewState.value = ViewState.Loading
        viewModelScope.launch {
            val result: Result<SearchResponse> = omdbRepo.addToFavorites(query)
            val exception: String? = result.exceptionOrNull()?.message
            val favoriteItems: List<FavoriteItem>? = result.getOrNull()?.favoriteResult

            val viewState = when{
                favoriteItems?.isEmpty() == true -> ViewState.Failure.NoMediaItemsFound
                favoriteItems.isNullOrEmpty() -> {
                    if (exception != null) ViewState.Failure.WhileFetching(exception)
                    else ViewState.Failure.SomethingWentWrong
                }
                else -> ViewState.FavoriteSuccess(favoriteItems)
            }
            _favoriteViewState.value = viewState
        }
    }
}