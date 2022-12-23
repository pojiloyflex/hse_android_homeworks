package io.github.hse.albumwatcher.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumResponse(@Json(name = "response") val albums: Albums) : Parcelable

@Parcelize
data class PhotoResponse(@Json(name = "response") val photos: Photos) : Parcelable