package io.github.hse.albumwatcher.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    @Json(name = "id") val albumId: String,
    @Json(name = "owner_id") val owner_id: String,
    @Json(name = "size") val size: Int,
    @Json(name = "title") val title: String?,
): Parcelable