package com.kakaobank.imageselector.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kakaobank.imageselector.databinding.ItemViewListBinding
import domain.model.ThumbnailModel

class ViewListAdapter : PagingDataAdapter<ThumbnailModel.Element, ViewListAdapter.ItemViewHolder>(Companion) {

    var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemViewListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind()
    }

    inner class ItemViewHolder(private val binding: ItemViewListBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            with(binding) {
                root.setOnClickListener {
                    onItemClick?.invoke(bindingAdapterPosition)
                }
            }
        }

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