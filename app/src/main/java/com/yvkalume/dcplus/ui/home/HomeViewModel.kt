package com.yvkalume.dcplus.ui.home

import com.airbnb.mvrx.MavericksViewModel
import com.yvkalume.model.presentation.HomeData
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeViewModel(
    initialState: HomeViewState
) : MavericksViewModel<HomeViewState>(initialState),KoinComponent {
    private val presenter: HomePresenter by inject()

    init {
        getData()
    }

    private fun getData() = viewModelScope.launch {
        val trendingFlow = presenter.getTrendingEpisodes()
        val genreFlow = presenter.getGenres()
        combine(trendingFlow,genreFlow) { trending, genres ->
            HomeData(trending,genres)
        }.execute {
            copy(homeData = it)
        }
    }
}