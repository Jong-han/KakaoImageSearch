package com.jh.kakaoimagesearch.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.jh.kakaoimagesearch.base.BaseViewHolder
import com.jh.kakaoimagesearch.data.remote.response.Document
import com.jh.kakaoimagesearch.databinding.ActivityMainItemBinding

class MainAdapter: PagingDataAdapter<Document, BaseViewHolder>(MainDiffUtil()){

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityMainItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

}

class MainViewHolder(private val binding: ActivityMainItemBinding): BaseViewHolder(binding) {

    override fun onBind(item: Any) {
        if (item is Document) {
            binding.document = item
        }
    }

}

class MainDiffUtil: DiffUtil.ItemCallback<Document>() {

    override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean {
        return oldItem.image_url == newItem.image_url
    }

    override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean {
        return oldItem == newItem
    }

}