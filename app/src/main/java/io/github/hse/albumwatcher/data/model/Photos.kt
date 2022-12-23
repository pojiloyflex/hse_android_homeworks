package io.github.hse.albumwatcher.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photos(
    @Json(name = "count") val count: Int,
    @Json(name = "items") val items: List<Photo>
) : Parcelable