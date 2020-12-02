package com.yvkalume.interactors

import com.yvkalume.model.domain.Episode
import com.yvkalume.model.domain.Genre
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
class EpisodeInteractor {

    suspend fun getAllEpisodes() = callbackFlow {
        offer(listOf<Episode>())
        awaitClose()
    }

    suspend fun getEpisodeById(uid: Int) = callbackFlow {
        offer(Episode("","",0))
        awaitClose()
    }

    suspend fun getEpisodeByGenre(genre: Genre) = callbackFlow {
        offer(listOf<Episode>())
        awaitClose()
    }

    suspend fun getFavoritesEpisodes() = callbackFlow {
        offer(listOf<Episode>())
        awaitClose()
    }

    suspend fun getEpisodesByKeywords(keywords: String) = callbackFlow {
        offer(listOf<Episode>())
        awaitClose()
    }
}