package com.yvkalume.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(
    val uid: String = " ",
    val title: String = " ",
) : Parcelable