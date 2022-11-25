package io.github.hse.songplaylist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: Song)

    @Update
    suspend fun updateSong(song: Song)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(album: Album)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtist(artist: Artist)

    @Query("DELETE FROM SONG where song_artist_id = :songId")
    suspend fun deleteSong(songId: Long)

    @Query("SELECT * FROM SONG where song_artist_id == :songId")
    fun getSong(songId: Long): Flow<Song>

    @Query("Select * From song JOIN artist ON song.song_artist_id = Artist.artist_id JOIN album on song_artist_id = Album.album_artist_id ")
    fun getSongs(): Flow<List<Song>>

}