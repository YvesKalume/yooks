package com.yvkalume.interactors


import com.yvkalume.model.domain.Genre
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
class GenreInteractor {
    suspend fun getAllGenres() = callbackFlow {
        offer(listOf<Genre>())
        awaitClose()
    }

    suspend fun getGenreById(uid: Int) = callbackFlow {
        offer(Genre("","", listOf()))
        awaitClose()
    }
}