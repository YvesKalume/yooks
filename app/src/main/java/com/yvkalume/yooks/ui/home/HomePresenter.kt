package com.yvkalume.yooks.ui.home

import com.yvkalume.interactors.BookInteractor
import com.yvkalume.interactors.GenreInteractor
import com.yvkalume.model.domain.Book
import com.yvkalume.model.util.toRowGenre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class HomePresenter(
    private val bookInteractor: BookInteractor,private val genreInteractor: GenreInteractor
    ) {

    suspend fun getTrendingEpisodes() = withContext(Dispatchers.IO) {
        bookInteractor.getAllBooks()
    }

    suspend fun getRowGenres() = flow {
        genreInteractor.getAllGenres().collect { genres ->
            bookInteractor.getAllBooks().collect { books ->
                val books = books.groupBy(Book::categoryUid).toRowGenre(genres)
                emit(books)
            }
        }
//        bookInteractor.getBooksGroupedByGenre()
    }
}