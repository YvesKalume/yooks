package com.yvkalume.yooks.ui.favorites

import com.yvkalume.interactors.BookInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritePresenter(private val bookInteractor: BookInteractor) {

    suspend fun getFavoriesEpisodes() = withContext(Dispatchers.IO) {
        bookInteractor.getFavoritesEpisodes()
    }

    suspend fun removeBookFromFavorite(bookUid: String) = withContext(Dispatchers.IO) {
        bookInteractor.removeBookFromFavorite(bookUid)
    }
}