package com.yvkalume.dcplus.ui.home

import com.yvkalume.interactors.EpisodeInteractor
import com.yvkalume.interactors.GenreInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomePresenter(
    private val episodeInteractor: EpisodeInteractor,
    private val genreInteractor: GenreInteractor
    ) {

    suspend fun getTrendingEpisodes() = withContext(Dispatchers.IO) {
        episodeInteractor.getAllEpisodes()
    }

    suspend fun getRowGenres() = withContext(Dispatchers.IO) {
        episodeInteractor.getEpisodeGroupedByGenre()
    }
}