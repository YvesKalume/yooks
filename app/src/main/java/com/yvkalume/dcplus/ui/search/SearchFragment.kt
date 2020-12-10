package com.yvkalume.dcplus.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.FragmentSearchBinding
import com.yvkalume.dcplus.adapter.groupie.BookSearchItem
import com.yvkalume.model.domain.Book


class SearchFragment : Fragment(R.layout.fragment_search), MavericksView {
    private val binding: FragmentSearchBinding by viewBinding()

    private val viewModel: SearchViewModel by activityViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = searchAdapter
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
        when(it.episodes) {
            is Loading -> Unit
            is Success -> populateResult(it.episodes.invoke())
            is Fail -> Unit
        }
    }
}