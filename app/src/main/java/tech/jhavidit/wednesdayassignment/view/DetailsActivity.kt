package tech.jhavidit.wednesdayassignment.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import tech.jhavidit.wednesdayassignment.R
import tech.jhavidit.wednesdayassignment.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        val songData = intent.extras?.getBundle("songData")
        binding.kind.text = songData?.getString("kind")
        binding.songArtist.text = songData?.getString("artistName")
        binding.songName.text = songData?.getString("trackName")
        binding.country.text = songData?.getString("country")
        val price = songData?.getString("trackPrice") + " " + songData?.getString("currency")
        binding.trackPrice.text = price
        Glide.with(this)
            .load(songData?.getString("picture")).into(binding.songPoster)

    }
}