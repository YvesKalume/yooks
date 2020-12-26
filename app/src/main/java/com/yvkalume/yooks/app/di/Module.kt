package com.yvkalume.yooks.app.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.yvkalume.yooks.MainViewModel
import com.yvkalume.yooks.ui.category.CategoryPresenter
import com.yvkalume.yooks.ui.favorites.FavoritePresenter
import com.yvkalume.yooks.ui.home.HomePresenter
import com.yvkalume.yooks.ui.preview.PreviewPresenter
import com.yvkalume.yooks.ui.search.SearchPresenter
import com.yvkalume.interactors.BookInteractor
import com.yvkalume.interactors.GenreInteractor
import com.yvkalume.interactors.UserInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

@ExperimentalCoroutinesApi
val interactorModule = module {
    single { BookInteractor(get(),get()) }
    single { GenreInteractor(get()) }
    single { UserInteractor(get()) }
}

@ExperimentalCoroutinesApi
val presenterModule = module {
    single { HomePresenter(get(),get()) }
    single { SearchPresenter(get()) }
    single { FavoritePresenter(get()) }
    single { CategoryPresenter(get()) }
    single { PreviewPresenter(get()) }
}

val firebaseModule = module {
    single { FirebaseFirestore.getInstance() }
    single { FirebaseStorage.getInstance() }
    single { FirebaseAuth.getInstance() }
}