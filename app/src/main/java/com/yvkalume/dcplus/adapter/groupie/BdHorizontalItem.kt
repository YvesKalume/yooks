package com.yvkalume.dcplus.adapter.groupie

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.ItemBdHorizontalBinding
import com.yvkalume.model.entity.Episode

class BdHorizontalItem(val episode: Episode) : BindableItem<ItemBdHorizontalBinding>() {
    override fun bind(viewBinding: ItemBdHorizontalBinding, position: Int) {
        viewBinding.run {
            title.text = episode.title
            roundedImageView.setImageResource(episode.image)
        }
    }

    override fun getLayout(): Int = R.layout.item_bd_horizontal

    override fun initializeViewBinding(view: View): ItemBdHorizontalBinding =
        ItemBdHorizontalBinding.bind(view)
}