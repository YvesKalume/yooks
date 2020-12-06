package com.yvkalume.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    val uid: String = "",
    val title: String = "",
    val imageUrl: String = "",
    val genreUid: String = "",
    val genreTitle: String = ""
) : Parcelable