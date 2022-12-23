package io.github.hse.albumwatcher.data

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
        const val ACCESS_TOKEN = "082ae447082ae447082ae447cf085630020082a082ae4476abe2a55c8d5ff8de9954c04"
        const val VERSION = "5.131"
    }
}

