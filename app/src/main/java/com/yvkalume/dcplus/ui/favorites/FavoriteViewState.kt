package com.yvkalume.dcplus.ui.favorites

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.yvkalume.model.domain.Episode

data class FavoriteViewState(
    val episodes : Async<List<Episode>> = Uninitialized
) : MavericksState