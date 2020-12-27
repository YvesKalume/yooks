package com.yvkalume.yooks.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.mvrx.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import com.yvkalume.yooks.R
import com.yvkalume.yooks.adapter.ImageSliderAdapter
import com.yvkalume.yooks.adapter.groupie.BookItem
import com.yvkalume.yooks.adapter.groupie.GenreItem
import com.yvkalume.yooks.adapter.groupie.GenreSection
import com.yvkalume.yooks.adapter.groupie.TrendingSection
import com.yvkalume.yooks.databinding.FragmentHomeBinding
import com.yvkalume.model.domain.Book
import com.yvkalume.model.presentation.RowGenre
import com.yvkalume.yooks.util.runLayoutAnimation


class HomeFragment : Fragment(R.layout.fragment_home), MavericksView {

    private val binding: FragmentHomeBinding by viewBinding()

    private val viewPool = RecyclerView.RecycledViewPool().apply {
        setMaxRecycledViews(0, 0)
    }

    private val viewModel: HomeViewModel by activityViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView
        recyclerView.adapter = homeAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(100)
        recyclerView.setRecycledViewPool(viewPool)
        recyclerView.runLayoutAnimation()
    }

    override fun invalidate() = withState(viewModel) {
        binding.loader.isVisible = it.homeData is Loading
        when(it.homeData) {
            is Success -> {
                populateTrendingList(it.homeData.invoke().trending)
                populateGenre(it.homeData.invoke().rowGenre)
                binding.recyclerView.isVisible = true
            }
            is Fail -> Unit
        }
    }

    private val homeAdapter = GroupAdapter<GroupieViewHolder>()

    private val genreAdapter = GroupAdapter<GroupieViewHolder>().apply {
        setOnItemClickListener { item, _ ->
            if (item is GenreItem)
                Toast.makeText(context, item.genreTitle,Toast.LENGTH_SHORT).show()
        }
    }

    private val sliderAdapter = ImageSliderAdapter()
    private fun populateTrendingList(data: List<Book>) {
        homeAdapter.clear()
        val trendingSection = Section().apply {
            sliderAdapter.addItem(data)
            update(listOf(TrendingSection(sliderAdapter)))
        }

        homeAdapter.add(trendingSection)
    }

    private fun populateGenre(data: List<RowGenre>) {
        val genreSection = Section().apply {
            val genreItems = data.map {
                val episodeItems= it.books.map { episode ->
                    BookItem(episode)
                }

                val episodeAdapter = getEpisodeAdapter()
                episodeAdapter.updateAsync(episodeItems)
                GenreItem(it,episodeAdapter,viewPool)
            }
            genreAdapter.updateAsync(genreItems)
            update(listOf(GenreSection(genreAdapter,viewPool)))
        }
        homeAdapter.add(genreSection)
    }

    private fun getEpisodeAdapter(): GroupAdapter<GroupieViewHolder> {
        return GroupAdapter<GroupieViewHolder>().apply {
            setOnItemClickListener { item, _ ->
                item as BookItem
                val directions = HomeFragmentDirections.actionPreviewFragment(item.book)
                findNavController().navigate(directions)
            }
        }
    }

}