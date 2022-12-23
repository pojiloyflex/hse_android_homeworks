package io.github.hse.albumwatcher.ui.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.hse.albumwatcher.data.VkRepository
import io.github.hse.albumwatcher.data.model.Albums
import io.github.hse.albumwatcher.utils.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AlbumViewModel(private val repository: VkRepository) : ViewModel() {
    val albums: MutableStateFlow<DataState<Albums>?> = MutableStateFlow(null)

    fun getAlbums(ownerId: String) {
        viewModelScope.launch {
            repository.getAlbums(ownerId).onEach {
                albums.emit(it)
            }.launchIn(viewModelScope)
        }
    }
}
