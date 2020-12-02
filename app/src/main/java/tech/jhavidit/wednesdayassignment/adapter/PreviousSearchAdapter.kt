package tech.jhavidit.wednesdayassignment.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.previous_search_item.view.*
import tech.jhavidit.wednesdayassignment.R
import tech.jhavidit.wednesdayassignment.models.PreviousSearch
import tech.jhavidit.wednesdayassignment.util.Constant
import tech.jhavidit.wednesdayassignment.view.SearchActivity

class PreviousSearchAdapter(private val context: Context) :
    RecyclerView.Adapter<PreviousSearchAdapter.ViewHolder>() {

    private var list: List<PreviousSearch> = ArrayList()

    fun setMusicItem(list: List<PreviousSearch>) {
        this.list = list
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.previous_search_item,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]
        holder.name.text = item.name
        holder.name.setOnClickListener {
            Constant.query = item.name
            val intent = Intent(context, SearchActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)

        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.name
    }
}