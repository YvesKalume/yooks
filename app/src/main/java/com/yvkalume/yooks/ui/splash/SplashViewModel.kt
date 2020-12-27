package com.yvkalume.yooks.ui.splash

import com.airbnb.mvrx.MavericksViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.yvkalume.yooks.ui.splash.SplashPresenter
import com.yvkalume.yooks.ui.splash.SplashViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class SplashViewModel(initialState: SplashViewState) : MavericksViewModel<SplashViewState>(initialState), KoinComponent {

    private val presenter by inject<SplashPresenter>()

    init {
        checkAuthentication()
    }

    private fun checkAuthentication() = viewModelScope.launch {
        delay(3000)
        presenter.userIsAuth().execute {
            copy(isAuthUser = it)
        }
    }
    fun signIn(account: GoogleSignInAccount) = viewModelScope.launch {
        presenter.signIn(account).execute {
            checkAuthentication()
            copy(isAuthUser = it)
        }
    }
}