package com.yvkalume.dcplus.ui.preview

import com.yvkalume.interactors.BookInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PreviewPresenter(private val bookInteractor: BookInteractor) {
    suspend fun getBookByUid(uid: String) = withContext(Dispatchers.IO) {
        bookInteractor.getBookByUid(uid)
    }
}