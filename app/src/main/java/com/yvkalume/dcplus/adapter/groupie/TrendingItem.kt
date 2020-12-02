package com.yvkalume.dcplus.adapter.groupie

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.ItemTrendingBinding
import com.yvkalume.model.domain.Episode

class TrendingItem(val episode: Episode) : BindableItem<ItemTrendingBinding>() {
    override fun bind(viewBinding: ItemTrendingBinding, position: Int) {
        viewBinding.run {
            imageView.setImageResource(episode.image)
        }
    }


    override fun getLayout(): Int = R.layout.item_trending

    override fun initializeViewBinding(view: View): ItemTrendingBinding =
        ItemTrendingBinding.bind(view)
}