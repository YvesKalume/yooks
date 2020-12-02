package com.yvkalume.dcplus.adapter.groupie

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.ItemEpisodeSearchBinding
import com.yvkalume.model.domain.Episode

class EpisodeSearchItem(private val bd: Episode) : BindableItem<ItemEpisodeSearchBinding>() {
    override fun bind(viewBinding: ItemEpisodeSearchBinding, position: Int) {
        viewBinding.run {
            title.text = bd.title
            appCompatImageView.setImageResource(bd.image)
        }
    }

    override fun getLayout(): Int = R.layout.item_episode_search

    override fun initializeViewBinding(view: View): ItemEpisodeSearchBinding =
        ItemEpisodeSearchBinding.bind(view)
}