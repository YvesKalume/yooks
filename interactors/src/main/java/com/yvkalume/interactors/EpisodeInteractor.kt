package com.yvkalume.interactors

import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.interactors.util.FirebasePath
import com.yvkalume.model.domain.Episode
import com.yvkalume.model.domain.Genre
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

@ExperimentalCoroutinesApi
class EpisodeInteractor(private val firestore: FirebaseFirestore) {

    suspend fun getAllEpisodes() = callbackFlow {
        val query = firestore.collection(FirebasePath.EPISODE)
        query.addSnapshotListener { value, error ->
            if (error != null || value == null) {
                close()
            }

            value?.toObjects(Episode::class.java)?.let {
                if (!isClosedForSend) offer(it)
            }

        }
        awaitClose()
    }



    suspend fun getEpisodeByGenreUid(uid: String) = callbackFlow {
        val query = firestore.collection("${FirebasePath.GENRE}/$uid/episodes")
        query.addSnapshotListener { value, error ->
            if (error != null || value == null) {
                close()
            }

            value?.toObjects(Episode::class.java)?.let {
                if (!isClosedForSend) offer(it)
            }

        }
    }

    suspend fun getFavoritesEpisodes() = callbackFlow {
        offer(listOf<Episode>())
        //TODO : get favorites episodes
        awaitClose()
    }


    fun getEpisodesByKeywords(keywords: String) : Flow<List<Episode>> = flow {
        getAllEpisodes().collect {
            val episodes = it.filter { episode ->
                episode.title.contains(keywords,ignoreCase = true)
            }
            emit(episodes)
        }
    }
}