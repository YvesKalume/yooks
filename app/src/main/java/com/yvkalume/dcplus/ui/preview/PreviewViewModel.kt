package com.yvkalume.dcplus.ui.preview

import com.airbnb.mvrx.MavericksViewModel
import com.yvkalume.model.domain.Book
import com.yvkalume.model.presentation.PreviewData
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class PreviewViewModel(initialState: PreviewViewState) : MavericksViewModel<PreviewViewState>(initialState), KoinComponent {
    private val presenter: PreviewPresenter by inject()
    fun load(uid: String) = viewModelScope.launch {
        presenter.getBookByUid(uid).map {
            PreviewData(book = it)
        }.execute {
            copy(previewData = it)
        }
    }
}