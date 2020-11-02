package com.test.cleanArchRoomTest.episode.view.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.cleanArchRoomTest.R
import com.test.cleanArchRoomTest.databinding.EpisodesItemListBinding

class EpisodeAdapter(
    private var onShowDetail: ShowDetail
) : RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    private var items: MutableList<String> = mutableListOf()

    private var layoutInflater: LayoutInflater? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpisodeAdapter.EpisodeViewHolder {
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

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.setData(items[position])
    }

    fun fillData(items: MutableList<String>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class EpisodeViewHolder(var binding: EpisodesItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: String) {
            binding.episode = data.substring(0, data.length - 1)
            binding.selectArrow.setOnClickListener {
                val value = data.substringAfterLast("/")
                onShowDetail.onShowDetailClicked(value.substring(0, value.length - 1))
            }
        }
    }
}


