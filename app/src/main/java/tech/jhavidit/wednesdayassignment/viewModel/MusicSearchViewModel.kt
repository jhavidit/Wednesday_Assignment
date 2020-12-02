package tech.jhavidit.wednesdayassignment.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.jhavidit.wednesdayassignment.models.MusicItem
import tech.jhavidit.wednesdayassignment.models.MusicItemLocal
import tech.jhavidit.wednesdayassignment.models.PreviousSearch
import tech.jhavidit.wednesdayassignment.repository.MusicSearchRepository
import tech.jhavidit.wednesdayassignment.room.MusicDatabase

class MusicSearchViewModel(application: Application) : AndroidViewModel(application) {

    val showMusicList: LiveData<MusicItem>
    val showProgress: LiveData<Boolean>
    val readAllData: LiveData<List<MusicItemLocal>>
    val previousData : LiveData<List<PreviousSearch>>
    val repository: MusicSearchRepository

    init {
        val musicDao = MusicDatabase.getDatabase(application).musicDao()
        repository = MusicSearchRepository(application, musicDao)
        this.showMusicList = repository.showMusicList
        this.showProgress = repository.showProgress
        readAllData = repository.readAllData
        previousData = repository.previousData
    }

    fun addMusicData(musicItemLocal: MusicItemLocal)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMusicData(musicItemLocal)
        }
    }
    fun addPreviousSearch(previousSearch: PreviousSearch)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPreviousSearch(previousSearch)
        }
    }
    fun deleteAllSong()
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllSong()
        }
    }

    fun getMusicData(searchItem: String) {
        repository.getMusicData(searchItem)
    }

}