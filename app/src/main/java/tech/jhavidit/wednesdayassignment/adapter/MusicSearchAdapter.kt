package tech.jhavidit.wednesdayassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.music_item.view.*
import tech.jhavidit.wednesdayassignment.R
import tech.jhavidit.wednesdayassignment.models.MusicResult

class MusicSearchAdapter(private val context: Context) :
    RecyclerView.Adapter<MusicSearchAdapter.ViewHolder>() {

    private var list: List<MusicResult> = ArrayList()

    fun setMusicItem(list: List<MusicResult>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.music_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = list[position]
        holder.songName.text = item.trackName
        holder.songArtist.text = item.artistName
        Glide.with(context)
            .load(item.artworkUrl100)
            .into(holder.songImage)

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val songImage: ImageView = view.song_image
        val songName: TextView = view.song_name
        val songArtist: TextView = view.song_artist

    }
}