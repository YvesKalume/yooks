package com.yvkalume.dcplus.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.FragmentSearchBinding
import com.yvkalume.dcplus.getEpisodes
import com.yvkalume.dcplus.adapter.groupie.BdSearchItem
import com.yvkalume.model.entity.Episode


class SearchFragment : Fragment(R.layout.fragment_search) {
    private val binding: FragmentSearchBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = searchAdapter
        populateResult(getEpisodes())

    }

    private val searchAdapter = GroupAdapter<GroupieViewHolder>()

    fun populateResult(bd: List<Episode>){
        searchAdapter.updateAsync(bd.map { BdSearchItem(it) })
    }
}