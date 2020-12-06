package com.yvkalume.dcplus.ui.favorites

import com.yvkalume.interactors.BookInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritePresenter(private val bookInteractor: BookInteractor) {
    suspend fun getFavoriesEpisodes() = withContext(Dispatchers.IO) {
        bookInteractor.getFavoritesEpisodes()
    }
}