package io.github.hse.albumwatcher.ui.photo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import io.github.hse.albumwatcher.data.model.Photo
import io.github.hse.albumwatcher.databinding.PhotoItemBinding

class PhotoAdapter: RecyclerView.Adapter<PhotoAdapter.AlbumViewHolder>() {
    private var photoList = mutableListOf<Photo>()

    inner class AlbumViewHolder(val binding: PhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding =
            PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        with(holder){
            val photo = photoList[position]
            with(photo){
                binding.photo.load(sizes[2].url)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPhotos(photos: List<Photo>) {
        if(photoList != photos) photoList = photos.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = photoList.size
}
