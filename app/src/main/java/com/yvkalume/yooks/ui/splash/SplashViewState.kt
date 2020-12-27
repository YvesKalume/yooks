package com.yvkalume.yooks.ui.splash

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized

data class SplashViewState(
    val isAuthUser : Async<Boolean> = Uninitialized,
    val message : Async<String> = Uninitialized
) : MavericksState