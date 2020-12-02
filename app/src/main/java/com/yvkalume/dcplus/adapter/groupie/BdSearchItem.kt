package com.yvkalume.dcplus.adapter.groupie

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.ItemBdSearchBinding
import com.yvkalume.model.domain.Episode

class BdSearchItem(private val bd: Episode) : BindableItem<ItemBdSearchBinding>() {
    override fun bind(viewBinding: ItemBdSearchBinding, position: Int) {
        viewBinding.run {
            title.text = bd.title
            appCompatImageView.setImageResource(bd.image)
        }
    }

    override fun getLayout(): Int = R.layout.item_bd_search

    override fun initializeViewBinding(view: View): ItemBdSearchBinding =
        ItemBdSearchBinding.bind(view)
}