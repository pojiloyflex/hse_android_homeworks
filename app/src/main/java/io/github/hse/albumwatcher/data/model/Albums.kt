package io.github.hse.albumwatcher.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Albums(
    @Json(name = "count") val count: Int,
    @Json(name = "items") val items: List<Album>
) : Parcelable
