package com.ceren.cerenapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ceren.cerenapplication.databinding.ItemListBinding
import java.util.UUID

data class Item(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String
)

class ItemAdapter : ListAdapter<Item, ItemAdapter.ItemViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(
        private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: Item) {
            binding.titleText.text = item.title
            binding.descriptionText.text = item.description
        }
    }
}

class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item) = 
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Item, newItem: Item) = 
        oldItem == newItem
} 