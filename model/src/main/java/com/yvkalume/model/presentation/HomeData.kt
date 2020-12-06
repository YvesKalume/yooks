package com.yvkalume.model.presentation

import com.yvkalume.model.domain.Episode
import com.yvkalume.model.domain.Genre

data class HomeData(
    val trending: List<Episode>,
    val rowGenre: List<RowGenre>
)