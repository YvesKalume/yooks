package com.yvkalume.yooks.ui.search

import com.yvkalume.interactors.BookInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchPresenter(private val bookInteractor: BookInteractor) {

    suspend fun loadAll() = withContext(Dispatchers.IO) {
        bookInteractor.getAllBooks()
    }

    suspend fun getEpisodesByKeywords(keywords: String) = withContext(Dispatchers.IO) {
        bookInteractor.getBooksByKeywords(keywords)
    }
}