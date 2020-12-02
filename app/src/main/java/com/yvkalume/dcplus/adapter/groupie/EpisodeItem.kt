package com.yvkalume.dcplus.adapter.groupie

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.ItemEpisodeBinding
import com.yvkalume.model.domain.Episode

class EpisodeItem(private val episode: Episode) : BindableItem<ItemEpisodeBinding>() {
    override fun bind(viewBinding: ItemEpisodeBinding, position: Int) {
        viewBinding.run {
            title.text = episode.title
            appCompatImageView.setImageResource(episode.image)
        }
    }

    override fun getLayout(): Int = R.layout.item_episode

    override fun initializeViewBinding(view: View): ItemEpisodeBinding =
        ItemEpisodeBinding.bind(view)
}