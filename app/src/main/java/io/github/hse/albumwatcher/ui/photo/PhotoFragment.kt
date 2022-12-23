package io.github.hse.albumwatcher.ui.photo

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import io.github.hse.albumwatcher.R
import io.github.hse.albumwatcher.databinding.FragmentPhotoBinding
import io.github.hse.albumwatcher.ext.lazyStringArgument
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoFragment : Fragment(R.layout.fragment_photo) {
    private val viewBinding by viewBinding(FragmentPhotoBinding::bind)
    private val viewModel: PhotoViewModel by viewModel()
    private val ownerId by lazyStringArgument(KEY_OWNER_ID)
    private val albumId by lazyStringArgument(KEY_ALBUM_ID)
    private val adapter: PhotoAdapter = PhotoAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        getPhotos()
        observePhotos()
    }

    private fun initViews() {
        viewBinding.recyclerViewPhotos.adapter = adapter
        viewBinding.recyclerViewPhotos.layoutManager = GridLayoutManager(context, 2)
    }

    private fun getPhotos() {
        viewModel.getPhotos(ownerId, albumId)
    }

    private fun observePhotos() {
        viewModel.photos.onEach {
            adapter.setPhotos(it?.items ?: listOf())
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    companion object {
        val TAG = PhotoFragment::class.qualifiedName!!
        private const val KEY_ALBUM_ID = "KEY_ALBUM_ID"
        private const val KEY_OWNER_ID = "KEY_OWNER_ID"
        fun newInstance(
            owner_id: String,
            album_id: String
        ) = PhotoFragment().apply {
            arguments = bundleOf(
                KEY_ALBUM_ID to album_id,
                KEY_OWNER_ID to owner_id
            )
        }
    }
}