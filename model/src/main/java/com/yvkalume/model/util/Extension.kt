package com.yvkalume.model.util

import com.yvkalume.model.domain.Book
import com.yvkalume.model.domain.Genre
import com.yvkalume.model.presentation.RowGenre

fun Map<String,List<Book>>.toRowGenre(genres: List<Genre>): ArrayList<RowGenre> {
    val data = arrayListOf<RowGenre>()
    for (item in this) {
        val genre = genres.find { it.uid == item.key }
        if (genre != null) {
            data += RowGenre(title = genre.title,books = item.value)
        }
    }
    return data
}