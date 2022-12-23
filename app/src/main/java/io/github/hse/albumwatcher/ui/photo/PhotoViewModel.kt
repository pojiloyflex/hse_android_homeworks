package io.github.hse.albumwatcher.ui.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.hse.albumwatcher.data.VkRepository
import io.github.hse.albumwatcher.data.model.Photos
import io.github.hse.albumwatcher.utils.DataState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PhotoViewModel(private val repository: VkRepository) : ViewModel() {
    val photos: MutableStateFlow<DataState<Photos>?> = MutableStateFlow(null)

    fun getPhotos(ownerId: String?, albumId: String?) {
        viewModelScope.launch {
            if (ownerId != null && albumId != null) {
                repository.getPhotos(ownerId, albumId).onEach {
                    photos.emit(it)
                }.launchIn(viewModelScope)
            }
        }
    }
}