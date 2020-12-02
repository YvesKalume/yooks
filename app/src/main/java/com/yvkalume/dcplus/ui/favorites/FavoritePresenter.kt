package com.yvkalume.dcplus.ui.favorites

import com.yvkalume.interactors.EpisodeInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritePresenter(private val episodeInteractor: EpisodeInteractor) {
    suspend fun getFavoriesEpisodes() = withContext(Dispatchers.IO) {
        episodeInteractor.getFavoritesEpisodes()
    }
}