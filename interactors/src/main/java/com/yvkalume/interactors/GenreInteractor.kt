package com.yvkalume.interactors


import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.interactors.util.FirebasePath
import com.yvkalume.model.domain.Genre
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
class GenreInteractor(private val firestore: FirebaseFirestore) {
    suspend fun getAllGenres() = callbackFlow {
        val query = firestore.collection(FirebasePath.GENRE)
        query.addSnapshotListener { value, error ->
            if (error != null || value == null) {
                close()
            }

            value?.toObjects(Genre::class.java)?.let {
                if (!isClosedForSend) offer(it)
            }
        }
        awaitClose()
    }
}