package com.yvkalume.dcplus.ui.category

import com.yvkalume.interactors.EpisodeInteractor
import com.yvkalume.model.domain.Genre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryPresenter(private val episodeInteractor: EpisodeInteractor) {

    suspend fun getEpisodeByGenre(genre: Genre) = withContext(Dispatchers.IO) {
        episodeInteractor.getEpisodeByGenre(genre)
    }
}