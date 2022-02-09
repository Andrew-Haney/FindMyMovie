package com.example.feature_favorite.adapter.viewholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_favorite.databinding.ItemFavoriteBinding
import com.example.omdb.response.FavoriteItem
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.example.feature_favorite.R
import com.example.omdb.local.dao.FavoritesDao

class FavoriteItemViewHolder (
    private val binding: ItemFavoriteBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bindFavoriteItem(favoriteItem: FavoriteItem) {
            binding.tvFavoriteTitle.text = favoriteItem.title
            binding.ivFavoritePoster.load(favoriteItem.poster) {
                crossfade(true)
                crossfade(durationMillis = 500)
                scale(Scale.FIT)
                placeholder(com.example.findmymovie.R.drawable.no_image)
                transformations(RoundedCornersTransformation(25f))
                build()
            }
        }

    companion object {

        fun newInstance(parent: ViewGroup) =
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ).let { binding ->
                FavoriteItemViewHolder(binding)
            }
    }
}