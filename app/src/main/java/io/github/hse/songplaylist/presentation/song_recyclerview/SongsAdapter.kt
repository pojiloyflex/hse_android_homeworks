package io.github.hse.songplaylist.presentation.song_recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.hse.songplaylist.R
import io.github.hse.songplaylist.data.Song
import io.github.hse.songplaylist.databinding.PlaylistItemBinding

class SongsAdapter(
    private val listOfSongs: MutableList<Song>,
    private val deleteSong: (Long) -> Unit,
    private val editSong: (Long) -> Unit
) : RecyclerView.Adapter<SongsAdapter.SongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding =
            PlaylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        with(holder) {
            val song = listOfSongs[position]
            with(song) {
                binding.songNameTextview.text = holder.itemView.context.getString(R.string.song_name, name)
                binding.albumTextview.text = holder.itemView.context.getString(R.string.album, album.albumName)
                binding.artistNameTextview.text = holder.itemView.context.getString(R.string.song_artist, artist.artistName)

                binding.deleteSongButton.setOnClickListener {
                    listOfSongs.removeAt(position)
                    notifyItemChanged(position)
                    deleteSong(song.artistId)
                }

                binding.playlistItem.setOnClickListener{
                    editSong(song.artistId)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfSongs.size
    }

    inner class SongViewHolder(val binding: PlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}