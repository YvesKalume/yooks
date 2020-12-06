package com.yvkalume.dcplus.ui.home

import com.yvkalume.interactors.BookInteractor
import com.yvkalume.interactors.GenreInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomePresenter(
    private val bookInteractor: BookInteractor
    ) {

    suspend fun getTrendingEpisodes() = withContext(Dispatchers.IO) {
        bookInteractor.getAllBooks()
    }

    suspend fun getRowGenres() = withContext(Dispatchers.IO) {
        bookInteractor.getBooksGroupedByGenre()
    }
}