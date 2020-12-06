package com.yvkalume.model.presentation

import com.yvkalume.model.domain.Book

data class HomeData(
    val trending: List<Book>,
    val rowGenre: List<RowGenre>
)