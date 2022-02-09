package com.example.feature_favorite.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.feature_favorite.adapter.viewholder.FavoriteItemViewHolder
import com.example.omdb.response.FavoriteItem

class FavoriteItemsAdapter: ListAdapter<FavoriteItem, FavoriteItemViewHolder> (DiffItemCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriteItemViewHolder.newInstance(parent)

    override fun onBindViewHolder(holder: FavoriteItemViewHolder, position: Int) {
        holder.bindFavoriteItem(getItem(position))
    }

   companion object {

       private val DiffItemCallBack =object: DiffUtil.ItemCallback<FavoriteItem>() {
           override fun areContentsTheSame(oldItem: FavoriteItem, newItem: FavoriteItem): Boolean {
               return oldItem == newItem
           }

           override fun areItemsTheSame(oldItem: FavoriteItem, newItem: FavoriteItem): Boolean {
               return oldItem.imdbID == newItem.imdbID
           }
       }
   }
}