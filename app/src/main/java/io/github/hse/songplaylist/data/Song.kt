package io.github.hse.songplaylist.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Song")
data class Song(
    var name: String,
    @Embedded
    var artist: Artist,
    @Embedded
    var album: Album,
    @ColumnInfo(name = "song_artist_id")
    @PrimaryKey val artistId: Long = 0,
)
