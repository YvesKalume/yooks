package com.yvkalume.interactors

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.interactors.util.FirebasePath
import com.yvkalume.model.domain.Book
import com.yvkalume.model.presentation.RowGenre
import com.yvkalume.model.util.toRowGenre
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

@ExperimentalCoroutinesApi
class BookInteractor(private val firestore: FirebaseFirestore, private val auth: FirebaseAuth) {

    private val currentUserUid by lazy {
        auth.currentUser?.uid ?: throw CancellationException("Unauthenticated user !")
    }

    suspend fun getAllBooks() = callbackFlow {
        val query = firestore.collection(FirebasePath.BOOK)
        query.addSnapshotListener { value, error ->
            if (error != null || value == null) {
                close()
            }

            value?.toObjects(Book::class.java)?.let {
                if (!isClosedForSend) offer(it)
            }

        }
        awaitClose()
    }


    suspend fun getBookByUid(uid: String) = callbackFlow {
        val query = firestore.document("${FirebasePath.BOOK}/$uid")
        query.addSnapshotListener { value, error ->
            if (error != null || value == null) {
                close()
            }
            value?.toObject(Book::class.java)?.let {
                if (!isClosedForSend) offer(it)
            }
        }
        awaitClose()
    }

    suspend fun getBooksByGenreUid(uid: String) = callbackFlow {
        val query = firestore.collection("${FirebasePath.GENRE}/$uid/books")
        query.addSnapshotListener { value, error ->
            if (error != null || value == null) {
                close()
            }

            value?.toObjects(Book::class.java)?.let {
                if (!isClosedForSend) offer(it)
            }

        }
        awaitClose()
    }

    suspend fun getBooksGroupedByGenre() : Flow<List<RowGenre>> = flow {
        getAllBooks().collect {
            val genres = it.groupBy(Book::genreTitle).toRowGenre()
            emit(genres)
        }
    }

    suspend fun getFavoritesEpisodes() = callbackFlow {
        //TODO : change path
        val query = firestore.collection("users/$currentUserUid/favorites")
        query.addSnapshotListener { value, error ->
            if (error != null || value == null) {
                close()
            }

            value?.toObjects(Book::class.java)?.let {
                if (!isClosedForSend) offer(it)
            }

        }

        awaitClose()
    }


    fun getBooksByKeywords(keywords: String) : Flow<List<Book>> = flow {
        getAllBooks().collect {
            val books = it.filter { book ->
                book.title.contains(keywords,ignoreCase = true)
            }
            emit(books)
        }
    }
}