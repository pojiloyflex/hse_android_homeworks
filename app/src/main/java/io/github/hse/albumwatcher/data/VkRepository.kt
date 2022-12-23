package io.github.hse.albumwatcher.data

import android.util.Log
import io.github.hse.albumwatcher.data.model.Albums
import io.github.hse.albumwatcher.data.model.Photos
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VkRepository(
    private val apiService: VkApiService
) {
    suspend fun getPhotos(ownerId: String, albumId: String): Flow<Photos> = flow {
        try {
            emit(apiService.getPhotos(ownerId, albumId).photos)
        } catch (e: Exception){
            Log.e("Error", e.toString())
        }
    }

    suspend fun getAlbums(ownerId: String): Flow<Albums> = flow {
        try {
            emit(apiService.getAlbums(ownerId).albums)
        } catch (e: Exception){
            Log.e("Error", e.toString())
        }
    }
}