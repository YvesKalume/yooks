package com.yvkalume.yooks.ui.category

import com.yvkalume.interactors.BookInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryPresenter(private val bookInteractor: BookInteractor) {

    suspend fun getEpisodeByGenreUid(uid: String) = withContext(Dispatchers.IO) {
        bookInteractor.getBooksByGenreUid(uid)
    }
}