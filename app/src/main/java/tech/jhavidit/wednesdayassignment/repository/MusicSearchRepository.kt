package tech.jhavidit.wednesdayassignment.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.jhavidit.wednesdayassignment.models.MusicItem
import tech.jhavidit.wednesdayassignment.models.MusicItemLocal
import tech.jhavidit.wednesdayassignment.models.PreviousSearch
import tech.jhavidit.wednesdayassignment.network.APIClient
import tech.jhavidit.wednesdayassignment.room.MusicDao

class MusicSearchRepository(private val application: Application, val musicDao: MusicDao) {

    val showMusicList = MutableLiveData<MusicItem>()
    val showProgress = MutableLiveData<Boolean>()

    val readAllData: LiveData<List<MusicItemLocal>> = musicDao.getMusicData()
    val previousData: LiveData<List<PreviousSearch>> = musicDao.getPreviousSearch()

    suspend fun addMusicData(musicItemLocal: MusicItemLocal) {
        musicDao.insertData(musicItemLocal)
    }

    suspend fun addPreviousSearch(previousSearch: PreviousSearch) {
        musicDao.insertPreviousSearch(previousSearch)
    }

    suspend fun deleteAllSong() {
        musicDao.deleteAllSong()
    }

    fun getMusicData(searchItem: String) {
        showProgress.value = true
        val retrofitService = APIClient.getClient(application)
        retrofitService.getCountryList(searchItem).enqueue(object : Callback<MusicItem> {
            override fun onFailure(call: Call<MusicItem>, t: Throwable) {
                showProgress.value = false
                Log.d("error", t.message.toString())
            }

            override fun onResponse(call: Call<MusicItem>, response: Response<MusicItem>) {
                showProgress.value = false
                Log.d("success", response.toString())
                showMusicList.value = response.body()
            }

        })

    }
}