package com.yvkalume.yooks.ui.splash

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.yvkalume.interactors.UserInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SplashPresenter(private val interactor: UserInteractor) {
    suspend fun signIn(account: GoogleSignInAccount) = withContext(Dispatchers.IO) {
        interactor.signIn(account)
    }

    suspend fun userIsAuth() = withContext(Dispatchers.IO) {
        interactor.userIsAuth()
    }
}