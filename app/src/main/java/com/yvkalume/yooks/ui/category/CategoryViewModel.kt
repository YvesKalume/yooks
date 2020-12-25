package com.yvkalume.yooks.ui.category

import com.airbnb.mvrx.MavericksViewModel
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class CategoryViewModel (
    initialState: CategoryViewState
) : MavericksViewModel<CategoryViewState>(initialState), KoinComponent {

    private val presenter: CategoryPresenter by inject()

    fun getData(genreUid: String) = viewModelScope.launch {
        presenter.getEpisodeByGenreUid(genreUid).execute {
            copy(episodes = it)
        }
    }

}