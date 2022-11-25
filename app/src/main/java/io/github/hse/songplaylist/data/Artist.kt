package io.github.hse.songplaylist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Artist")
data class Artist(
    @ColumnInfo(name = "artist_name")
    var artistName: String,
    var birthDate: String = "",
    var surname:String = "",
    @ColumnInfo(name = "artist_id")
    @PrimaryKey var artistId: Long,
)