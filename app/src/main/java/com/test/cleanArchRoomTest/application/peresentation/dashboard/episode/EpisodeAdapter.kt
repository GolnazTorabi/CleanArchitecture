package com.test.cleanArchRoomTest.application.peresentation.dashboard.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.cleanArchRoomTest.R
import com.test.cleanArchRoomTest.databinding.EpisodesItemListBinding

class EpisodeAdapter(
    var list: ArrayList<String>,
    private var onShowDetail: ShowDetail
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
        return EpisodeViewHolder(binding)
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

class EpisodeViewHolder(var binding: EpisodesItemListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun setData(data: String) {
        binding.episodeId.text = data.substring(0, data.length - 1)
    }
}
