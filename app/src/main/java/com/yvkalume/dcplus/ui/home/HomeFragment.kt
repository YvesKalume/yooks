package com.yvkalume.dcplus.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.adapter.ImageSliderAdapter
import com.yvkalume.dcplus.databinding.FragmentHomeBinding
import com.yvkalume.dcplus.getGenres
import com.yvkalume.dcplus.getTrends
import com.yvkalume.dcplus.groupie.*
import com.yvkalume.model.entity.Episode
import com.yvkalume.model.entity.Genre


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    private val sections = arrayListOf<Section>()

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
      binding.recyclerView.apply {
          adapter = homeAdapter
          setHasFixedSize(true)
          setRecycledViewPool(viewPool)
        }

        populateTrendingList(getTrends())
        populateGenre(getGenres())
    }

    private val homeAdapter = GroupAdapter<GroupieViewHolder>()

    private val genreAdapter = GroupAdapter<GroupieViewHolder>().apply {
        setOnItemClickListener { item, _ ->
            if (item is GenreItem)
                Toast.makeText(context,"${item.genreTitle}",Toast.LENGTH_SHORT).show()
        }
    }

    private val sliderAdapter = ImageSliderAdapter()
    private fun populateTrendingList(data: List<Episode>) {
        val trendingSection = Section().apply {
            data.map { sliderAdapter.addItem(it) }
            update(listOf(TrendingSection(sliderAdapter)))
        }

        sections.add(trendingSection)
        homeAdapter.updateAsync(sections)
    }

    private fun populateGenre(data: List<Genre>) {

        val genreSection = Section().apply {
            val genreItems = data.map {
                val episodeItems= it.episode.map { episode ->
                    BdItem(episode)
                }

                val episodeAdapter = getEpisodeAdapter()
                episodeAdapter.updateAsync(episodeItems)
                GenreItem(it,episodeAdapter,viewPool)
            }
            genreAdapter.updateAsync(genreItems)
            update(listOf(GenreSection(genreAdapter,viewPool)))
        }


        sections.add(genreSection)
        homeAdapter.updateAsync(sections)
    }

    private fun getEpisodeAdapter(): GroupAdapter<GroupieViewHolder> {
        return GroupAdapter<GroupieViewHolder>().apply {
            setOnItemClickListener { item, _ ->
                item as BdItem
                findNavController().navigate(R.id.action_homeFragment_to_previewFragment)
            }
        }
    }

}