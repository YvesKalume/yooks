package com.yvkalume.dcplus.ui.preview

import com.yvkalume.interactors.BookInteractor
import com.yvkalume.model.domain.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PreviewPresenter(private val bookInteractor: BookInteractor) {
    suspend fun getBookByUid(uid: String) = withContext(Dispatchers.IO) {
        bookInteractor.getBookByUid(uid)
    }

    suspend fun addToFavorites(book: Book) = withContext(Dispatchers.IO) {
        bookInteractor.addToFavorites(book)
    }

    suspend fun removeBookFromFavorite(bookUid: String) = withContext(Dispatchers.IO) {
        bookInteractor.removeBookFromFavorite(bookUid)
    }

    suspend fun isFavorite(uid: String) = withContext(Dispatchers.IO) {
        bookInteractor.isFavorite(uid)
    }
}