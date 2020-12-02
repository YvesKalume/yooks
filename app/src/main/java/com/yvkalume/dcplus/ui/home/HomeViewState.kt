package com.yvkalume.dcplus.ui.home

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.yvkalume.model.presentation.HomeData

data class HomeViewState (
    val homeData: Async<HomeData> = Uninitialized
    ) : MavericksState
