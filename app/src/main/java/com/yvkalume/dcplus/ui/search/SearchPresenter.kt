package com.yvkalume.dcplus.ui.search

import com.yvkalume.interactors.EpisodeInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchPresenter(private val episodeInteractor: EpisodeInteractor) {

    suspend fun loadAll() = withContext(Dispatchers.IO) {
        episodeInteractor.getAllEpisodes()
    }

    suspend fun getEpisodesByKeywords(keywords: String) = withContext(Dispatchers.IO) {
        episodeInteractor.getEpisodesByKeywords(keywords)
    }
}