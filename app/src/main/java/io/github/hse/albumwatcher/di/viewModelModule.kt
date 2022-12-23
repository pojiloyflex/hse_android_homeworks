package io.github.hse.albumwatcher.di

import io.github.hse.albumwatcher.ui.album.AlbumViewModel
import io.github.hse.albumwatcher.ui.photo.PhotoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        PhotoViewModel(
            repository = get()
        )
    }
    viewModel {
        AlbumViewModel(
            repository = get()
        )
    }
}