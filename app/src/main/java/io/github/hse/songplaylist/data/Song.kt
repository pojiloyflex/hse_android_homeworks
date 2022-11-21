package io.github.hse.songplaylist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class Song(
    var name: String,
    var artist: String,
    var album: String,
    @PrimaryKey (autoGenerate = true) val id: Long = 0,
)
