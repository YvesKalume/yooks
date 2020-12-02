package com.yvkalume.dcplus.adapter.groupie

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.ItemBdBinding
import com.yvkalume.model.domain.Episode

class BdItem(private val episode: Episode) : BindableItem<ItemBdBinding>() {
    override fun bind(viewBinding: ItemBdBinding, position: Int) {
        viewBinding.run {
            title.text = episode.title
            appCompatImageView.setImageResource(episode.image)
        }
    }

    override fun getLayout(): Int = R.layout.item_bd

    override fun initializeViewBinding(view: View): ItemBdBinding =
        ItemBdBinding.bind(view)
}