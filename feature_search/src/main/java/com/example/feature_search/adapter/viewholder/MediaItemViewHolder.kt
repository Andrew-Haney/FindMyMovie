package com.example.feature_search.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.example.feature_search.databinding.ItemMediaBinding
import com.example.findmymovie.R
import com.example.omdb.local.dao.FavoritesDao
import com.example.omdb.response.FavoriteItem
import com.example.omdb.response.MediaItem
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.coroutineContext

class MediaItemViewHolder(
    private val binding: ItemMediaBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindMediaItem(mediaItem: MediaItem) {
        binding.tvTitle.text = mediaItem.title
        binding.ivPoster.load(mediaItem.poster) {
            scale(Scale.FIT)
            crossfade(true)
            crossfade(500)
            placeholder(R.drawable.no_image)
            transformations(RoundedCornersTransformation(25f))
            build()
        }
    }

    companion object {
        fun newInstance(parent: ViewGroup) = ItemMediaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).let { binding -> MediaItemViewHolder(binding) }
    }
}

