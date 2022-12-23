package io.github.hse.albumwatcher.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    @Json(name = "owner_id") val ownerId: String,
    @Json(name = "album_id")val albumId: String,
    @Json(name = "date") val date: String,
    @Json(name = "sizes") val sizes: List<Size>,
) : Parcelable