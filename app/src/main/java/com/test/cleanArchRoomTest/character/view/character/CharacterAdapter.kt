package com.test.cleanArchRoomTest.character.view.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.cleanArchRoomTest.R
import com.test.cleanArchRoomTest.character.domain.model.CharactersData
import com.test.cleanArchRoomTest.databinding.CharactersItemListBinding

class CharacterAdapter(
    private var onCharacterClicked: CharacterClicked
) : RecyclerView.Adapter<CharacterAdapter.DashBoardViewHolder>() {

    private var items: MutableList<CharactersData> = mutableListOf()


    private var layoutInflater: LayoutInflater? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DashBoardViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding: CharactersItemListBinding = DataBindingUtil.inflate(
            layoutInflater!!,
            R.layout.characters_item_list,
            parent,
            false
        )
        return DashBoardViewHolder(binding, onCharacterClicked)


    }

    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {
        holder.setData(items[position])
    }

    fun fillData(items: MutableList<CharactersData>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    class DashBoardViewHolder(
        var binding: CharactersItemListBinding,
        private var onCharacterClicked: CharacterClicked
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: CharactersData) {
            binding.charactersData = data
            binding.characterLayout.setOnClickListener {
                onCharacterClicked.onCharacterClicked(data.characterId)
            }
        }

    }
}