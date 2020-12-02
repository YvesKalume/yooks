package com.yvkalume.dcplus

import com.yvkalume.model.domain.Episode
import com.yvkalume.model.domain.Genre

val imageList = arrayListOf(
    R.drawable.skyrock,
    R.drawable.one_piece,
    R.drawable.dragonball,
    R.drawable.fairytale,
    R.drawable.fairytale,
    R.drawable.fox_of_siku
)

fun getTrends(): ArrayList<Episode> {
    val trends = arrayListOf<Episode>()
    for (i in 1..3) {
        trends.add(Episode("$i","Item $i", imageList.random()))
    }
    return trends
}

fun getEpisodes(): ArrayList<Episode> {
    val episodes = arrayListOf<Episode>()
    for (i in 1..8) {
        episodes.add(Episode("$i","Episode #$i", imageList.random()))
    }
    return episodes
}

fun getGenres(): ArrayList<Genre> {
    val genres = arrayListOf<Genre>()
    for (i in 1..5) {
        genres.add(Genre("$i","Category $i", getEpisodes()))
    }
    return genres
}