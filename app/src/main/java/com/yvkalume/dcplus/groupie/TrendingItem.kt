package com.yvkalume.dcplus.groupie

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.ItemGenreBinding
import com.yvkalume.dcplus.databinding.ItemTrendingBinding
import com.yvkalume.model.entity.Episode

class TrendingItem(val episode: Episode) : BindableItem<ItemTrendingBinding>() {
    override fun bind(viewBinding: ItemTrendingBinding, position: Int) {

    }


    override fun getLayout(): Int = R.layout.item_trending

    override fun initializeViewBinding(view: View): ItemTrendingBinding =
        ItemTrendingBinding.bind(view)
}