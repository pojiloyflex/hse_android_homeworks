package io.github.hse.songplaylist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: Song)

    @Update
    suspend fun updateSong(song: Song)

    @Query ("DELETE FROM songs where id = :songId")
    suspend fun deleteSong(songId: Long)

    @Query("SELECT * FROM songs where id == :songId")
    fun getSong(songId: Long): Flow<Song>

    @Query("SELECT * FROM songs")
    fun getSongs(): Flow<List<Song>>
}