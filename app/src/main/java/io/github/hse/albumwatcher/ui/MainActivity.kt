package io.github.hse.albumwatcher.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import io.github.hse.albumwatcher.R
import io.github.hse.albumwatcher.ui.album.AlbumFragment
import io.github.hse.albumwatcher.ui.photo.PhotoFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        with(supportFragmentManager) {
            if (findFragmentById(R.id.main_container) == null) {
                commit {
                    add(
                        R.id.main_container,
                        AlbumFragment.newInstance(),
                        AlbumFragment.TAG,
                    )
                }
            }
        }
    }

    fun navigateToPhotos(ownerId: String,albumId: String) {
        addToBackStack(PhotoFragment.newInstance(ownerId,albumId), PhotoFragment.TAG)
    }

    private fun addToBackStack(
        fragment: Fragment,
        tag: String,
    ) {
        val fragmentManager = supportFragmentManager
        fragmentManager.commit {
            replace(R.id.main_container, fragment, tag)
            addToBackStack(tag)
        }
    }
}