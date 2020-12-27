package com.yvkalume.yooks.ui.search

import com.airbnb.mvrx.MavericksViewModel
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class SearchViewModel(initialState: SearchViewState) : MavericksViewModel<SearchViewState>(initialState), KoinComponent {

    private val presenter: SearchPresenter by inject()

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        presenter.loadAll().execute {
            copy(episodes = it )
        }
    }
}