package io.github.hse.albumwatcher.ui.album


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import io.github.hse.albumwatcher.ui.MainActivity
import io.github.hse.albumwatcher.R
import io.github.hse.albumwatcher.data.model.Albums
import io.github.hse.albumwatcher.databinding.FragmentAlbumBinding
import io.github.hse.albumwatcher.utils.Constants
import io.github.hse.albumwatcher.utils.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumFragment : Fragment(R.layout.fragment_album) {
    private val viewBinding by viewBinding(FragmentAlbumBinding::bind)
    private val viewModel: AlbumViewModel by viewModel()
    private val adapter: AlbumAdapter = AlbumAdapter(::navigateToAlbum)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeAlbums()
    }

    private fun initViews() {
        viewBinding.recyclerViewAlbums.adapter = adapter
        viewBinding.recyclerViewAlbums.layoutManager = GridLayoutManager(context, 2)
        viewBinding.searchButton.setOnClickListener {
            val ownerId = viewBinding.vkIdEditText.text.toString()
            if (ownerId.isEmpty()) {
                Toast.makeText(context, Constants.ERROR_TEXT, Toast.LENGTH_LONG).show()
            } else viewModel.getAlbums(ownerId)
        }
    }

    private fun observeAlbums() {
        viewModel.albums.onEach {
            when(it){
                is DataState.Success<Albums> -> {
                    adapter.setAlbums(it.data.items)
                    viewBinding.loader.visibility = View.INVISIBLE
                    viewBinding.recyclerViewAlbums.visibility = View.VISIBLE
                }
                is DataState.Error -> {
                    viewBinding.errorText.visibility = View.VISIBLE
                    viewBinding.recyclerViewAlbums.visibility = View.INVISIBLE
                    viewBinding.loader.visibility = View.INVISIBLE
                }
                is DataState.Loading -> {
                    viewBinding.loader.visibility = View.VISIBLE
                    viewBinding.loader.setProgress(0, true)
                    viewBinding.loader.max = 100
                }
                null -> {}
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun navigateToAlbum(ownerId: String, albumId: String){
        (activity as MainActivity).navigateToPhotos(ownerId, albumId)
    }

    companion object {
        val TAG = AlbumFragment::class.qualifiedName!!
        fun newInstance() = AlbumFragment()
    }
}