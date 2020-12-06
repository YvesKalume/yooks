package com.yvkalume.dcplus.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.mvrx.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.adapter.ImageSliderAdapter
import com.yvkalume.dcplus.adapter.groupie.EpisodeItem
import com.yvkalume.dcplus.adapter.groupie.GenreItem
import com.yvkalume.dcplus.adapter.groupie.GenreSection
import com.yvkalume.dcplus.adapter.groupie.TrendingSection
import com.yvkalume.dcplus.databinding.FragmentHomeBinding
import com.yvkalume.model.domain.Episode
import com.yvkalume.model.domain.Genre
import com.yvkalume.model.presentation.RowGenre


class HomeFragment : Fragment(R.layout.fragment_home), MavericksView {

    private val binding: FragmentHomeBinding by viewBinding()

    private val sections = arrayListOf<Section>()

    private val viewPool = RecyclerView.RecycledViewPool().apply {
        setMaxRecycledViews(0, 0)
    }

    private val viewModel: HomeViewModel by activityViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
          adapter = homeAdapter
          setHasFixedSize(true)
          setRecycledViewPool(viewPool)
        }
    }

    override fun invalidate() = withState(viewModel) {
        when(it.homeData) {
            is Loading -> Unit
            is Success -> {
                populateTrendingList(it.homeData.invoke().trending)
                populateGenre(it.homeData.invoke().rowGenre)
            }
            is Fail -> Unit
        }
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

    private fun populateGenre(data: List<RowGenre>) {

        val genreSection = Section().apply {
            val genreItems = data.map {
                val episodeItems= it.episodes.map { episode ->
                    EpisodeItem(episode)
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
                item as EpisodeItem
                findNavController().navigate(R.id.action_homeFragment_to_previewFragment)
            }
        }
    }

}