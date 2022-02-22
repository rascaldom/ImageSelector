package com.kakaobank.imageselector.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kakaobank.imageselector.databinding.ItemViewListBinding
import domain.model.ThumbnailModel

class BookmarksAdapter : ListAdapter<ThumbnailModel.Element, BookmarksAdapter.ItemViewHolder>(Companion) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarksAdapter.ItemViewHolder {
        return ItemViewHolder(ItemViewListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BookmarksAdapter.ItemViewHolder, position: Int) {
        holder.bind()
    }

    inner class ItemViewHolder(val binding: ItemViewListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(binding) {
                data = getItem(bindingAdapterPosition)
            }
        }
    }

    companion object : DiffUtil.ItemCallback<ThumbnailModel.Element>() {
        override fun areItemsTheSame(oldItem: ThumbnailModel.Element, newItem: ThumbnailModel.Element): Boolean {
            return oldItem.thumbnailUrl == newItem.thumbnailUrl
        }

        override fun areContentsTheSame(oldItem: ThumbnailModel.Element, newItem: ThumbnailModel.Element): Boolean {
            return oldItem.dateTime == newItem.dateTime
        }
    }

}