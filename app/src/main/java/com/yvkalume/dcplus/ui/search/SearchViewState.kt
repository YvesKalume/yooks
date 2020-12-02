package com.yvkalume.dcplus.ui.search

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.yvkalume.model.domain.Episode

data class SearchViewState(
    val episodes: Async<List<Episode>> = Uninitialized
) : MavericksState