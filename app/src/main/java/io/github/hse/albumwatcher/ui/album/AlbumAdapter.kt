package io.github.hse.albumwatcher.ui.album

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.hse.albumwatcher.data.model.Album
import io.github.hse.albumwatcher.databinding.AlbumItemBinding

class AlbumAdapter(
    private val navigateToAlbum: (String, String) -> Unit
) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    private var albumList = mutableListOf<Album>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding =
            AlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        with(holder) {
            with(albumList[position]) {
                binding.albumSize.text = size.toString()
                binding.albumTitle.text = title
                binding.root.setOnClickListener {
                    navigateToAlbum(
                        albumList[position].owner_id,
                        albumList[position].albumId
                    )
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAlbums(albums: List<Album>) {
        if (albums != albumList) albumList = albums.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount() = albumList.size

    inner class AlbumViewHolder(val binding: AlbumItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
