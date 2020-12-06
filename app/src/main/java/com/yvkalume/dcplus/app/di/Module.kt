package com.yvkalume.dcplus.app.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.yvkalume.dcplus.ui.category.CategoryPresenter
import com.yvkalume.dcplus.ui.favorites.FavoritePresenter
import com.yvkalume.dcplus.ui.home.HomePresenter
import com.yvkalume.dcplus.ui.search.SearchPresenter
import com.yvkalume.interactors.BookInteractor
import com.yvkalume.interactors.GenreInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val interactorModule = module {
    single { BookInteractor(get()) }
    single { GenreInteractor(get()) }
}

@ExperimentalCoroutinesApi
val presenterModule = module {
    single { HomePresenter(get()) }
    single { SearchPresenter(get()) }
    single { FavoritePresenter(get()) }
    single { CategoryPresenter(get()) }
}

val firebaseModule = module {
    single { FirebaseFirestore.getInstance() }
    single { FirebaseStorage.getInstance() }
}