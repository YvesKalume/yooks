package com.yvkalume.dcplus.groupie

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import com.smarteist.autoimageslider.SliderViewAdapter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.ItemTrendingBinding
import com.yvkalume.dcplus.databinding.ItemTrendingCarouselBinding

class TrendingCarouselItem(
    private val adapter: SliderViewAdapter<*>
) : BindableItem<ItemTrendingCarouselBinding>() {
    override fun bind(viewBinding: ItemTrendingCarouselBinding, position: Int) {
        viewBinding.sliderView.setSliderAdapter(adapter)
    }

    override fun createViewHolder(itemView: View): com.xwray.groupie.viewbinding.GroupieViewHolder<ItemTrendingCarouselBinding> {
        return super.createViewHolder(itemView).also {
            it.binding.sliderView.apply {
                setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
                autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_RIGHT
                scrollTimeInSec = 3
                startAutoCycle()
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_trending_carousel

    override fun initializeViewBinding(view: View): ItemTrendingCarouselBinding =
        ItemTrendingCarouselBinding.bind(view)
}