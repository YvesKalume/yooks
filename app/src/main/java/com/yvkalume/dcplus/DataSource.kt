package com.yvkalume.dcplus

import com.yvkalume.model.entity.Episode
import com.yvkalume.model.entity.Genre
import com.yvkalume.model.entity.Serie

fun getTrends(): ArrayList<Serie> {
    val trends = arrayListOf<Serie>()
    for (i in 1..3) {
        trends.add(Serie("$i","Item $i"))
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
        val series = arrayListOf<Serie>()
        for (j in 1..4) {
            series.add(Serie("$j","item $j"))
        }
        genres.add(Genre("$i","Category $i",series))
    }
    return genres
}