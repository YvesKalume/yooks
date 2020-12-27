package com.yvkalume.yooks.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.yvkalume.yooks.R
import com.yvkalume.yooks.databinding.FragmentSearchBinding
import com.yvkalume.yooks.adapter.groupie.BookSearchItem
import com.yvkalume.model.domain.Book
import com.yvkalume.yooks.util.runLayoutAnimation


class SearchFragment : Fragment(R.layout.fragment_search), MavericksView {
    private val binding: FragmentSearchBinding by viewBinding()

    private val viewModel: SearchViewModel by activityViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = searchAdapter
        binding.recyclerView.runLayoutAnimation()
    }

    private val searchAdapter = GroupAdapter<GroupieViewHolder>().apply {
       setOnItemClickListener { item, view ->
           item as BookSearchItem
           val destinations = SearchFragmentDirections.actionPreviewFragment(item.book)
           findNavController().navigate(destinations)
       }
    }

    private fun populateResult(bd: List<Book>){
        searchAdapter.updateAsync(bd.map { BookSearchItem(it) })
    }

    override fun invalidate() = withState(viewModel) {
        binding.loader.isVisible = it.episodes is Loading
        when(it.episodes) {
            is Loading -> Unit
            is Success -> {
                populateResult(it.episodes.invoke())
                binding.recyclerView.isVisible = true
            }
            is Fail -> Unit
        }
    }
}