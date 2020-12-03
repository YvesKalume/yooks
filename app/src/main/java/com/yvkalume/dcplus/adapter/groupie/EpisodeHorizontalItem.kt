package com.yvkalume.dcplus.adapter.groupie

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.ItemEpisodeHorizontalBinding
import com.yvkalume.model.domain.Episode

class EpisodeHorizontalItem(val episode: Episode) : BindableItem<ItemEpisodeHorizontalBinding>() {
    override fun bind(viewBinding: ItemEpisodeHorizontalBinding, position: Int) {
        viewBinding.run {
            title.text = episode.title
            roundedImageView.setImageResource(episode.image)
        }
    }

    override fun getLayout(): Int = R.layout.item_episode_horizontal

    override fun initializeViewBinding(view: View): ItemEpisodeHorizontalBinding =
        ItemEpisodeHorizontalBinding.bind(view)
}