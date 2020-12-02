package tech.jhavidit.wednesdayassignment.room

import androidx.lifecycle.LiveData
import androidx.room.*
import tech.jhavidit.wednesdayassignment.models.MusicItemLocal
import tech.jhavidit.wednesdayassignment.models.PreviousSearch

@Dao
interface MusicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(list: MusicItemLocal)

    @Query(" SELECT * FROM music_list ORDER BY id ASC ")
    fun getMusicData(): LiveData<List<MusicItemLocal>>

    @Query("DELETE FROM music_list")
    suspend fun deleteAllSong()

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertPreviousSearch(list:PreviousSearch)

    @Ignore
    @Query("SELECT * FROM previous_search ORDER BY  id DESC LIMIT 10")
    fun getPreviousSearch(): LiveData<List<PreviousSearch>>
}