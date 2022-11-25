package io.github.hse.songplaylist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Album")
data class Album(
    var albumName: String,
    @PrimaryKey
    @ColumnInfo(name = "album_artist_id")
    var artistId: Long,
)