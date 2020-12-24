package com.yvkalume.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Book(
    val uid: String = "",
    val title: String = "",
    val imageUrl: String = "",
    val pdfUrl: String= "",
    val description: String= "",
    val author: String= "",
    val pages: String= "",
    val categoryUid: String = "",
    val createdAt: String = ""
) : Parcelable