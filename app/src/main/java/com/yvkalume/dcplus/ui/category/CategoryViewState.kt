package com.yvkalume.dcplus.ui.category

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.yvkalume.model.domain.Episode

data class CategoryViewState(
    val episode: Async<List<Episode>> = Uninitialized
) : MavericksState