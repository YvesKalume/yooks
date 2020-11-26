package com.yvkalume.dcplus.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.FragmentFavoriteBinding
import com.yvkalume.dcplus.getEpisodes
import com.yvkalume.dcplus.groupie.BdHorizontalItem
import com.yvkalume.model.entity.Episode

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val binding: FragmentFavoriteBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = favoriteAdapter
        populateFavorite(getEpisodes())
    }

    private val favoriteAdapter = GroupAdapter<GroupieViewHolder>()

    private fun populateFavorite(episodes: List<Episode>) {
        val episodesItems = episodes.map { BdHorizontalItem(it) }
        favoriteAdapter.updateAsync(episodesItems)
    }

}