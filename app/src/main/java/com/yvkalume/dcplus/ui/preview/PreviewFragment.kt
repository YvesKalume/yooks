package com.yvkalume.dcplus.ui.preview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.FragmentPreviewBinding
import com.yvkalume.dcplus.getEpisodes
import com.yvkalume.dcplus.groupie.BdHorizontalItem
import com.yvkalume.model.entity.Episode


class PreviewFragment : Fragment(R.layout.fragment_preview) {
    private val binding by lazy { FragmentPreviewBinding.inflate(layoutInflater) }

    private val viewPool = RecyclerView.RecycledViewPool().apply {
        setMaxRecycledViews(0, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = episodeAdapter
        binding.recyclerView.setRecycledViewPool(viewPool)
        populateEpisode(getEpisodes())
    }

    private val episodeAdapter = GroupAdapter<GroupieViewHolder>().apply {
        setOnItemClickListener { item, _ ->
            item as BdHorizontalItem
            Toast.makeText(context,item.episode.title,Toast.LENGTH_SHORT).show()
        }
    }

    private fun populateEpisode(episodes: List<Episode>) {

         episodes.map {
             episodeAdapter.add(BdHorizontalItem(it))
         }

    }
}