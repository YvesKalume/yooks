package com.yvkalume.dcplus.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.mvrx.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.FragmentFavoriteBinding
import com.yvkalume.dcplus.adapter.groupie.BookHorizontalItem
import com.yvkalume.dcplus.adapter.groupie.BookItem
import com.yvkalume.model.domain.Book

class FavoriteFragment : Fragment(R.layout.fragment_favorite), MavericksView {

    private val binding: FragmentFavoriteBinding by viewBinding()
    private val viewModel: FavoriteViewModel by activityViewModel()
    private val recyclerView by lazy { binding.recyclerView }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerview()
    }

    private val favoriteAdapter = GroupAdapter<GroupieViewHolder>().apply {
        setOnItemClickListener { item, _ ->
            item as BookHorizontalItem
            val directions = FavoriteFragmentDirections.actionPreviewFragment(item.book)
            findNavController().navigate(directions)
        }
    }

    private fun setUpRecyclerview() {
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
                val item = favoriteAdapter.getItem(viewHolder.adapterPosition) as BookHorizontalItem
                viewModel.removeBookFromFavorite(item.book.uid)
                favoriteAdapter.notifyDataSetChanged()
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun populateFavorite(books: List<Book>) {
        val episodesItems = books.map { BookHorizontalItem(it) }
        favoriteAdapter.updateAsync(episodesItems)
    }

    override fun invalidate() = withState(viewModel) {
        when(it.episodes) {
            is Loading -> Unit
            is Success -> populateFavorite(it.episodes.invoke())
            is Fail -> Unit
        }
    }
}