package io.github.hse.albumwatcher.data

import io.github.hse.albumwatcher.BuildConfig
import io.github.hse.albumwatcher.data.model.AlbumResponse
import io.github.hse.albumwatcher.data.model.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface VkApiService {

    @GET("photos.getAlbums")
    suspend fun getAlbums(
        @Query("owner_id") owner_id: String,
        @Query("v") version: String = VERSION,
        @Query("access_token") accessToken: String = ACCESS_TOKEN
    ): AlbumResponse

    @GET("photos.get")
    suspend fun getPhotos(
        @Query("owner_id") owner_id: String,
        @Query("album_id") album_id: String,
        @Query("v") version: String = VERSION,
        @Query("access_token") accessToken: String = ACCESS_TOKEN
    ): PhotoResponse

    private companion object {
        const val ACCESS_TOKEN = BuildConfig.API_KEY
        const val VERSION = "5.131"
    }
}

