package io.github.hse.songplaylist

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.hse.songplaylist.data.AppDatabase
import io.github.hse.songplaylist.data.Song
import io.github.hse.songplaylist.databinding.ActivityMainBinding
import io.github.hse.songplaylist.presentation.add_song.AddSongActivity
import io.github.hse.songplaylist.presentation.song_recyclerview.SongsAdapter
import io.github.hse.songplaylist.utils.Constants.SONG_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listOfSongs = arrayListOf<Song>()
    private val adapter = SongsAdapter(listOfSongs,::deleteSong,::editSong)
    private val database by lazy {
        AppDatabase.getDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPlayList()
        initAddSongButton()
    }

    override fun onStart() {
        observePlaylist()
        super.onStart()
    }

    private fun initAddSongButton() {
        binding.addSongButton.setOnClickListener {
            val intent = Intent(this, AddSongActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initPlayList(){
        binding.playlistRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observePlaylist(){
        lifecycleScope.launch(Dispatchers.IO) {
            database.getPlaylistDao().getSongs().collect {
                listOfSongs.addAll(it)
            }
        }
    }

    private fun deleteSong(id: Long){
        lifecycleScope.launch(Dispatchers.IO) {
            database.getPlaylistDao().deleteSong(id)
        }
    }

    private fun editSong(id: Long){
        val intent = Intent(this, AddSongActivity::class.java).apply {
            putExtra(SONG_ID, id)
        }
        startActivity(intent)
    }
}