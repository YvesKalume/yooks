package com.yvkalume.dcplus.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.FragmentFavoriteBinding
import com.yvkalume.dcplus.getEpisodes
import com.yvkalume.dcplus.adapter.groupie.BdHorizontalItem
import com.yvkalume.model.domain.Episode

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val binding: FragmentFavoriteBinding by viewBinding()
    private val recyclerView by lazy { binding.recyclerView }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerview()
        populateFavorite(getEpisodes())
    }

    private val favoriteAdapter = GroupAdapter<GroupieViewHolder>()

    fun setUpRecyclerview() {
        recyclerView.adapter = favoriteAdapter
        val swipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = favoriteAdapter.getItem(viewHolder.adapterPosition) as BdHorizontalItem
                Toast.makeText(context, item.episode.title,Toast.LENGTH_SHORT).show()
                favoriteAdapter.notifyItemRemoved(viewHolder.adapterPosition)
                //TODO : put deletetion confirmation dialogbox
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun populateFavorite(episodes: List<Episode>) {
        val episodesItems = episodes.map { BdHorizontalItem(it) }
        favoriteAdapter.updateAsync(episodesItems)
    }

}