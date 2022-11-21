package io.github.hse.songplaylist.presentation.add_song

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import io.github.hse.songplaylist.MainActivity
import io.github.hse.songplaylist.data.AppDatabase
import io.github.hse.songplaylist.data.Song
import io.github.hse.songplaylist.databinding.ActivityAddSongBinding
import io.github.hse.songplaylist.utils.Constants
import kotlinx.coroutines.launch

class AddSongActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddSongBinding
    private var isEdit = false
    private var generatedId: Long = 0
    private val database by lazy {
        AppDatabase.getDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoadSong()
        initSaveButton()
    }


    private fun initLoadSong() {
        lifecycleScope.launch {
            val songId = intent.getLongExtra(Constants.SONG_ID, NO_ID_PASSED)
            if (songId != NO_ID_PASSED) {
                database.getPlaylistDao().getSong(songId).collect { song ->
                    if (song != null) {
                        isEdit = true
                        binding.songNameEditText.editText?.setText(song.name)
                        binding.artistEditText.editText?.setText(song.artist)
                        binding.albumEditText.editText?.setText(song.album)
                        generatedId = song.id
                    }
                }
            } else isEdit = false
        }
    }

    private fun initSaveButton() {
        binding.saveButton.setOnClickListener {
            val areFieldsNotEmpty = binding.albumEditText.editText?.text.toString()
                .isNotEmpty() && binding.artistEditText.editText?.text.toString()
                .isNotEmpty() && binding.songNameEditText.editText?.text.toString().isNotEmpty()

            if (areFieldsNotEmpty) {
                saveSong()
                navigateBackToMainActivity()
            } else Toast.makeText(baseContext, "Please, fill all fields!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun saveSong() {
        lifecycleScope.launch {
            when (isEdit) {
                false -> {
                    database.getPlaylistDao().insertSong(
                        Song(
                            name = binding.songNameEditText.editText?.text.toString(),
                            artist = binding.artistEditText.editText?.text.toString(),
                            album = binding.albumEditText.editText?.text.toString()
                        )
                    )
                }
                true -> {
                    database.getPlaylistDao().updateSong(
                        Song(
                            name = binding.songNameEditText.editText?.text.toString(),
                            artist = binding.artistEditText.editText?.text.toString(),
                            album = binding.albumEditText.editText?.text.toString(),
                            id = generatedId
                        )
                    )
                }
            }
        }
    }

    private fun navigateBackToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private companion object {
        const val NO_ID_PASSED: Long = -1
    }

}