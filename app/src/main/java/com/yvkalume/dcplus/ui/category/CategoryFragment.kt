package com.yvkalume.dcplus.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.activityViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.adapter.groupie.BdItem
import com.yvkalume.dcplus.databinding.FragmentCategoryBinding
import com.yvkalume.model.domain.Episode


class CategoryFragment : Fragment(R.layout.fragment_category),MavericksView {

    private val binding: FragmentCategoryBinding by viewBinding()

    private val viewModel: CategoryViewModel by activityViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private val categoryAdapter = GroupAdapter<GroupieViewHolder>().apply {
        setOnItemClickListener { item, _ ->
            item as BdItem
            findNavController().navigate(R.id.action_homeFragment_to_previewFragment)
        }
    }

    fun populateEpisode(data: List<Episode>) {
        val episodeItems= data.map { episode ->
            BdItem(episode)
        }
        categoryAdapter.updateAsync(episodeItems)
    }

    override fun invalidate() {

    }

}