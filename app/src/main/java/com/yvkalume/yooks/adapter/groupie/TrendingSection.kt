package com.yvkalume.yooks.adapter.groupie

import android.view.View
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import com.smarteist.autoimageslider.SliderViewAdapter
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.yooks.R
import com.yvkalume.yooks.databinding.SectionTrendingBinding

class TrendingSection(
    private val adapter: SliderViewAdapter<*>
) : BindableItem<SectionTrendingBinding>() {
    override fun bind(viewBinding: SectionTrendingBinding, position: Int) {
        viewBinding.sliderView.setSliderAdapter(adapter)
    }

    override fun createViewHolder(itemView: View): com.xwray.groupie.viewbinding.GroupieViewHolder<SectionTrendingBinding> {
        return super.createViewHolder(itemView).also {
            it.binding.sliderView.apply {
                setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
                autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_RIGHT
                scrollTimeInSec = 3
                startAutoCycle()
            }
        }
    }

    override fun getLayout(): Int = R.layout.section_trending

    override fun initializeViewBinding(view: View): SectionTrendingBinding =
        SectionTrendingBinding.bind(view)
}