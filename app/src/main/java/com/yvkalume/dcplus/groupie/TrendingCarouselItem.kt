package com.yvkalume.dcplus.groupie

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.ItemTrendingBinding
import com.yvkalume.dcplus.databinding.ItemTrendingCarouselBinding

class TrendingCarouselItem(
    private val adapter: GroupAdapter<GroupieViewHolder>,
    private val viewPool: RecyclerView.RecycledViewPool
) : BindableItem<ItemTrendingCarouselBinding>() {
    override fun bind(viewBinding: ItemTrendingCarouselBinding, position: Int) {
        viewBinding.recyclerView.adapter = adapter
    }

    override fun createViewHolder(itemView: View): com.xwray.groupie.viewbinding.GroupieViewHolder<ItemTrendingCarouselBinding> {
        return super.createViewHolder(itemView).also {
            it.binding.recyclerView.apply {
                setRecycledViewPool(viewPool)
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_trending_carousel

    override fun initializeViewBinding(view: View): ItemTrendingCarouselBinding =
        ItemTrendingCarouselBinding.bind(view)
}