package com.example.feature_favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.feature_favorite.adapter.FavoriteItemsAdapter
import com.example.feature_favorite.databinding.FragmentFavoriteBinding
import com.example.feature_favorite.util.ViewState
import com.example.feature_favorite.viewmodel.FavoriteViewModel

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<FavoriteViewModel>()

    private val favoriteItemsAdapter by lazy { FavoriteItemsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentFavoriteBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews()
    }

    private fun initViews() = with(binding){
        rvFavoriteFragment.adapter = favoriteItemsAdapter

    }

    private fun initObservers() = with(viewModel){
        favoriteViewState.observe(viewLifecycleOwner) { state ->
            if(state is ViewState.FavoriteSuccess) favoriteItemsAdapter.submitList(state.favoriteItems)
            if(state is ViewState.Failure) handleError(state)
        }
    }

    private fun handleError(state: ViewState.Failure) {
        val errorMsg = when(state) {
            is ViewState.Failure.SomethingWentWrong -> "Something went wrong."
            is ViewState.Failure.NoMediaItemsFound -> "No media found in favorites."
            is ViewState.Failure.InvalidQuery -> "Invalid Query"
            is ViewState.Failure.WhileFetching -> state.exception
        }
        Log.d("Favorite Fragment", "handle error: $errorMsg")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}