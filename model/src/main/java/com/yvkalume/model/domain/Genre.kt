package com.yvkalume.model.domain

data class Genre(
    val uid: String = " ",
    val title: String = " ",
    val episode: List<Episode>? = null
)