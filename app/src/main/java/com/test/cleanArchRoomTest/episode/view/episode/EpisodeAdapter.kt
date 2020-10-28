package com.test.cleanArchRoomTest.episode.view.episode

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.cleanArchRoomTest.R
import com.test.cleanArchRoomTest.databinding.EpisodesItemListBinding

class EpisodeAdapter(
    var list: ArrayList<String>,
    private var onShowDetail: ShowDetail,
    val activity: Activity
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var layoutInflater: LayoutInflater? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding: EpisodesItemListBinding = DataBindingUtil.inflate(
            layoutInflater!!,
            R.layout.episodes_item_list,
            parent,
            false
        )
        return EpisodeViewHolder(binding,activity)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EpisodeViewHolder) {
            holder.setData(list[position])
            holder.binding.selectArrow.setOnClickListener {
                val data = list[position].substringAfterLast("/")
                onShowDetail.onShowDetailClicked(data.substring(0, data.length - 1))
            }
        }
    }
}

class EpisodeViewHolder(var binding: EpisodesItemListBinding,var activity: Activity) :
    RecyclerView.ViewHolder(binding.root) {
    fun setData(data: String) {
        binding.episodeId.text = activity.getString(R.string.episode_episode,data.substring(0, data.length - 1))
    }
}
