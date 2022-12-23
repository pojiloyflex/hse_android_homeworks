package io.github.hse.albumwatcher.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Size(
    @Json(name = "height") val height: Int,
    @Json(name = "type") val type: String,
    @Json(name = "url") val url: String
): Parcelable