package com.yvkalume.dcplus.ui.favorites

import com.airbnb.mvrx.MavericksViewModel
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class FavoriteViewModel(initialState: FavoriteViewState) : MavericksViewModel<FavoriteViewState>(initialState), KoinComponent {
    private val presenter: FavoritePresenter by inject()

    init {
        getData()
    }

    private fun getData() = viewModelScope.launch {
        presenter.getFavoriesEpisodes().execute {
            copy(episodes = it)
        }
    }

    fun removeBookFromFavorite(bookUid: String) = viewModelScope.launch {
        presenter.removeBookFromFavorite(bookUid)
    }
}