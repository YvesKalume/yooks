package com.yvkalume.model.util

import com.yvkalume.model.domain.Book
import com.yvkalume.model.presentation.RowGenre

fun Map<String,List<Book>>.toRowGenre(): ArrayList<RowGenre> {
    val data = arrayListOf<RowGenre>()
    for (item in this) {
        data += RowGenre(title = item.key,books = item.value)
    }
    return data
}