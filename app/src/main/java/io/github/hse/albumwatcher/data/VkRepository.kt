package io.github.hse.albumwatcher.data

import io.github.hse.albumwatcher.data.model.Albums
import io.github.hse.albumwatcher.data.model.Photos
import io.github.hse.albumwatcher.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VkRepository(
    private val apiService: VkApiService
) {
    suspend fun getPhotos(ownerId: String, albumId: String): Flow<DataState<Photos>> = flow {
        emit(DataState.Loading)
        try {
            emit(DataState.Success(apiService.getPhotos(ownerId, albumId).photos))
        } catch (e: Exception){
            emit(DataState.Error(e))
        }
    }

    suspend fun getAlbums(ownerId: String): Flow<DataState<Albums>> = flow {
        emit(DataState.Loading)
        try {
            emit(DataState.Success(apiService.getAlbums(ownerId).albums))
        } catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}