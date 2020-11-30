package com.yvkalume.dcplus

import com.yvkalume.model.entity.Episode
import com.yvkalume.model.entity.Genre

fun getTrends(): ArrayList<Episode> {
    val trends = arrayListOf<Episode>()
    for (i in 1..3) {
        trends.add(Episode("$i","Item $i"))
    }
    return trends
}

fun getEpisodes(): ArrayList<Episode> {
    val episodes = arrayListOf<Episode>()
    for (i in 1..8) {
        episodes.add(Episode("$i","Episode #$i"))
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