package tech.jhavidit.wednesdayassignment.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.android.ext.android.bind
import tech.jhavidit.wednesdayassignment.R
import tech.jhavidit.wednesdayassignment.adapter.MusicSearchAdapter
import tech.jhavidit.wednesdayassignment.adapter.PreviousSearchAdapter
import tech.jhavidit.wednesdayassignment.databinding.ActivitySearchBinding
import tech.jhavidit.wednesdayassignment.models.MusicItemLocal
import tech.jhavidit.wednesdayassignment.models.MusicResult
import tech.jhavidit.wednesdayassignment.models.PreviousSearch
import tech.jhavidit.wednesdayassignment.util.Constant
import tech.jhavidit.wednesdayassignment.util.InternetConnectivity
import tech.jhavidit.wednesdayassignment.viewModel.MusicSearchViewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: MusicSearchViewModel
    private lateinit var adapter: MusicSearchAdapter
    private lateinit var adapterHistory: PreviousSearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        viewModel = ViewModelProvider(this).get(MusicSearchViewModel::class.java)
        adapterHistory = PreviousSearchAdapter(this)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        adapter = MusicSearchAdapter(this)

        binding.recyclerView.adapter = adapter
        binding.recyclerViewHistory.adapter = adapterHistory
        if (Constant.query.isNotEmpty()) {

            binding.searchBar.setQuery(Constant.query, true)
            submit(Constant.query)
        }

        viewModel.readAllData.observe(this, Observer {
            val list = ArrayList<MusicResult>()
            for (i in it) {
                val musicResult = MusicResult(
                    i.kind,
                    i.artistName,
                    i.artworkUrl100,
                    i.country,
                    i.trackPrice,
                    i.trackName,
                    i.collectionName,
                    i.currency
                )
                list.add(musicResult)
            }
            if(list.isNotEmpty()) {
                binding.start.visibility = GONE
                binding.recyclerView.visibility = VISIBLE
                adapter.setMusicItem(list)
            }
        })

        binding.searchBar.setOnCloseListener {
            binding.recyclerViewHistory.visibility = GONE
            binding.recyclerView.visibility = VISIBLE
            false
        }
        binding.searchBar.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {


            override fun onQueryTextSubmit(p0: String?): Boolean {
                Constant.query = ""
                submit(p0!!)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                viewModel.previousData.observe(this@SearchActivity, Observer {
                    adapterHistory.setMusicItem(it)
                })
                binding.recyclerViewHistory.visibility = VISIBLE
                binding.recyclerView.visibility = GONE

                return false
            }


        })


    }

    fun submit(p0: String) {
        if (!InternetConnectivity.isNetworkAvailable(this@SearchActivity)!!)
            Toast.makeText(this, "Internet Unavailable", Toast.LENGTH_SHORT).show()
        else if (p0.isNotEmpty()) {
            val previousSearch = PreviousSearch(0, p0)
            binding.recyclerView.visibility = GONE
            binding.start.visibility = GONE
            binding.recyclerViewHistory.visibility = GONE
            viewModel.showProgress.observe(this@SearchActivity, Observer {
                if (it) {

                    binding.progressCircular.visibility = VISIBLE

                } else {
                    binding.progressCircular.visibility = GONE
                    binding.recyclerView.visibility = VISIBLE
                }
            })
            viewModel.addPreviousSearch(previousSearch)
            viewModel.getMusicData(p0)
            viewModel.showMusicList.observe(this@SearchActivity, Observer {
                if (it.resultCount == 0) {
                    binding.progressCircular.setAnimation(R.raw.empty_list)
                    binding.progressCircular.visibility = VISIBLE
                    binding.recyclerView.visibility = GONE
                    binding.recyclerViewHistory.visibility = GONE
                    Toast.makeText(this, "No data avaiable", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.deleteAllSong()

                    for (i in it.results) {
                        val musicItemLocal = MusicItemLocal(
                            0,
                            i.kind,
                            i.artistName,
                            i.artworkUrl100,
                            i.country,
                            i.trackPrice,
                            i.trackName,
                            i.collectionName,
                            i.currency
                        )

                        viewModel.addMusicData(musicItemLocal)
                    }

                    adapter.setMusicItem(it.results)
                }
            })
        }
        binding.recyclerViewHistory.visibility = GONE

    }
}


