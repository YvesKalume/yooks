package com.yvkalume.dcplus.adapter.groupie

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.ItemEpisodeBinding
import com.yvkalume.model.domain.Episode
import com.yvkalume.util.setImageUrl

class EpisodeItem(private val episode: Episode) : BindableItem<ItemEpisodeBinding>() {
    override fun bind(viewBinding: ItemEpisodeBinding, position: Int) {
        viewBinding.run {
            title.text = episode.title
            appCompatImageView.setImageUrl(episode.imageUrl)
        }
    }

    override fun getLayout(): Int = R.layout.item_episode

    override fun initializeViewBinding(view: View): ItemEpisodeBinding =
        ItemEpisodeBinding.bind(view)
}