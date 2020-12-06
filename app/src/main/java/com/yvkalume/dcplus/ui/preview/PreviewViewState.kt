package com.yvkalume.dcplus.ui.preview

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.yvkalume.model.presentation.PreviewData

data class PreviewViewState(
    val previewData: Async<PreviewData> = Uninitialized
) : MavericksState