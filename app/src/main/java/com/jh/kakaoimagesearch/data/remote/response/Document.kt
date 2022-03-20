package com.jh.kakaoimagesearch.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Document(
    val collection: String,
    val datetime: String,
    val display_sitename: String,
    val doc_url: String,
    val height: Int,
    val image_url: String,
    val thumbnail_url: String,
    val width: Int
): Parcelable