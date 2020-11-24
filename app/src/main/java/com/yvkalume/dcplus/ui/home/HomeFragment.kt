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
import com.yvkalume.dcplus.databinding.FragmentHomeBinding
import com.yvkalume.dcplus.getGenres
import com.yvkalume.dcplus.getTrends
import com.yvkalume.dcplus.groupie.*
import com.yvkalume.model.entity.Genre
import com.yvkalume.model.entity.Serie


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }


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

    private val homeAdapter = GroupAdapter<GroupieViewHolder>().apply {
        setOnItemClickListener { item, _ ->
            if (item is GenreItem)
                Toast.makeText(context,"${item.genreTitle}",Toast.LENGTH_SHORT).show()
        }
    }

    private val trendingAdapter = GroupAdapter<GroupieViewHolder>().apply {
        setOnItemClickListener { item, _ ->
            item as TrendingItem
            Toast.makeText(context, item.serie.title,Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_homeFragment_to_previewFragment)
        }

    }

    private val genreAdapter = GroupAdapter<GroupieViewHolder>()

    private fun populateTrendingList(data: List<Serie>) {
        val trendingSection = Section().apply {
            val trendingItems = data.map { TrendingItem(it) }
            trendingAdapter.updateAsync(trendingItems)
            update(listOf(TrendingCarouselItem(trendingAdapter,viewPool)))
        }
        homeAdapter.add(trendingSection)
    }

    private fun populateGenre(data: List<Genre>) {

            val genreItems = data.map {
                val serieItems= it.series.map { serie ->
                    BdItem(serie)
                }
                val serieAdapter = GroupAdapter<GroupieViewHolder>().apply {
                    setOnItemClickListener { item, _ ->
                        item as BdItem
                        findNavController().navigate(R.id.action_homeFragment_to_previewFragment)
                    }
                }
                serieAdapter.updateAsync(serieItems)
                GenreItem(it,serieAdapter,viewPool)
            }

        homeAdapter.addAll(genreItems)
    }

}