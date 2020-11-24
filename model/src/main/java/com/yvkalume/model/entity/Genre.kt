package com.yvkalume.model.entity

data class Genre(
    val uid: String,
    val title: String,
    val series: List<Serie>
)