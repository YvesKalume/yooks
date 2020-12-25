package com.yvkalume.yooks.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.activityViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.yvkalume.yooks.R
import com.yvkalume.yooks.adapter.groupie.BookItem
import com.yvkalume.yooks.databinding.FragmentCategoryBinding
import com.yvkalume.model.domain.Book


class CategoryFragment : Fragment(R.layout.fragment_category),MavericksView {

    private val binding: FragmentCategoryBinding by viewBinding()

    private val viewModel: CategoryViewModel by activityViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private val categoryAdapter = GroupAdapter<GroupieViewHolder>().apply {
        setOnItemClickListener { item, _ ->
            item as BookItem
            findNavController().navigate(R.id.action_preview_fragment)
        }
    }

    fun populateEpisode(data: List<Book>) {
        val episodeItems= data.map { episode ->
            BookItem(episode)
        }
        categoryAdapter.updateAsync(episodeItems)
    }

    override fun invalidate() {

    }

}