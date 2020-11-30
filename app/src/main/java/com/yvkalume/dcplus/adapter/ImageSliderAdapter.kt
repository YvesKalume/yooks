package com.yvkalume.dcplus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.smarteist.autoimageslider.SliderViewAdapter
import com.yvkalume.dcplus.databinding.ItemTrendingBinding
import com.yvkalume.model.entity.Episode
import com.yvkalume.util.setImageUrl

class ImageSliderAdapter : SliderViewAdapter<CustomViewHolder>() {

    private val items = mutableListOf<Episode>()

    fun addItem(item: Episode) {
        items.add(item)
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
        binding.title.text = items[position].title
        binding.executePendingBindings()
    }
}