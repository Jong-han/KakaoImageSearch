package com.jh.kakaoimagesearch.base

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(item: Any, itemClickListener: (View, Any)->Unit)
}