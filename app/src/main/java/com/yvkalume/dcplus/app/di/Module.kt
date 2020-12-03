package com.yvkalume.dcplus.app.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.yvkalume.dcplus.ui.category.CategoryPresenter
import com.yvkalume.dcplus.ui.favorites.FavoritePresenter
import com.yvkalume.dcplus.ui.home.HomePresenter
import com.yvkalume.dcplus.ui.search.SearchPresenter
import com.yvkalume.interactors.EpisodeInteractor
import com.yvkalume.interactors.GenreInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val interactorModule = module {
    single { EpisodeInteractor() }
    single { GenreInteractor() }
}

@ExperimentalCoroutinesApi
val presenterModule = module {
    single { HomePresenter(get(),get()) }
    single { SearchPresenter(get()) }
    single { FavoritePresenter(get()) }
    single { CategoryPresenter(get()) }
}

val firebaseModule = module {
    single { FirebaseFirestore.getInstance() }
    single { FirebaseStorage.getInstance() }
}