package com.yvkalume.dcplus.groupie

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.ItemBdBinding
import com.yvkalume.model.entity.Serie

class BdItem(private val serie: Serie) : BindableItem<ItemBdBinding>() {
    override fun bind(viewBinding: ItemBdBinding, position: Int) {
        viewBinding.title.text = serie.title
    }

    override fun getLayout(): Int = R.layout.item_bd

    override fun initializeViewBinding(view: View): ItemBdBinding =
        ItemBdBinding.bind(view)
}