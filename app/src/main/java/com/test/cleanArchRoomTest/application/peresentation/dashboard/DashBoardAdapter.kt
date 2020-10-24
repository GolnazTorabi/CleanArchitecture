package com.test.cleanArchRoomTest.application.peresentation.dashboard

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.cleanArchRoomTest.R
import com.test.cleanArchRoomTest.databinding.CharactersItemListBinding
import com.test.cleanArchRoomTest.domain.model.CharactersData

class DashBoardAdapter(
    private var activity: Activity,
    var list: ArrayList<CharactersData>,
    private var onCharacterClicked: CharacterClicked
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var layoutInflater: LayoutInflater? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding: CharactersItemListBinding = DataBindingUtil.inflate(
            layoutInflater!!,
            R.layout.characters_item_list,
            parent,
            false
        )
        return DashBoardViewHolder(binding, activity)


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DashBoardViewHolder) {
            holder.setData(list[position])
            holder.binding.characterLayout.setOnClickListener {
                onCharacterClicked.onCharacterClicked(list[position].characterId)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class DashBoardViewHolder(
        var binding: CharactersItemListBinding,
        private val activity: Activity
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: CharactersData) {
            binding.characterName.text = data.name
            binding.characterStatus.text = data.status
            binding.lastLocationSeen.text = data.location?.name
            Glide.with(activity.baseContext)
                .load(data.image)
                .into(binding.characterImage)

        }

    }
}