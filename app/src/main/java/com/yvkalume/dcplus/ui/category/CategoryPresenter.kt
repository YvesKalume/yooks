package com.yvkalume.dcplus.ui.category

import com.yvkalume.interactors.EpisodeInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryPresenter(private val episodeInteractor: EpisodeInteractor) {

    suspend fun getEpisodeByGenreUid(uid: String) = withContext(Dispatchers.IO) {
        episodeInteractor.getEpisodeByGenreUid(uid)
    }
}