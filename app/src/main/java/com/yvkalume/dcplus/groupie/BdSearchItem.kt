package com.yvkalume.dcplus.groupie

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.ItemBdSearchBinding
import com.yvkalume.model.entity.Episode

class BdSearchItem(private val bd: Episode) : BindableItem<ItemBdSearchBinding>() {
    override fun bind(viewBinding: ItemBdSearchBinding, position: Int) {
        viewBinding.title.text = bd.title
    }

    override fun getLayout(): Int = R.layout.item_bd_search

    override fun initializeViewBinding(view: View): ItemBdSearchBinding =
        ItemBdSearchBinding.bind(view)
}