package com.yvkalume.model.domain

data class Genre(
    val uid: String = " ",
    val title: String = " ",
    var episodes: ArrayList<Episode>? = null
)