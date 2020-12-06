package com.yvkalume.dcplus.ui.category

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.yvkalume.model.domain.Book

data class CategoryViewState(
    val episodes: Async<List<Book>> = Uninitialized
) : MavericksState