package com.yvkalume.dcplus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.smarteist.autoimageslider.SliderViewAdapter
import com.yvkalume.dcplus.databinding.ItemTrendingBinding
import com.yvkalume.model.domain.Book
import com.yvkalume.util.setImageUrl

class ImageSliderAdapter : SliderViewAdapter<CustomViewHolder>() {

    private var items = listOf<Book>()

    fun addItem(items: List<Book>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTrendingBinding.inflate(layoutInflater, parent, false)

        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: CustomViewHolder, position: Int) {
        val binding = viewHolder.binding as ItemTrendingBinding
        //TODO : getItem Image Url
        binding.imageView.setImageUrl(items[position].imageUrl)
        binding.executePendingBindings()
    }
}