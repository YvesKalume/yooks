package com.yvkalume.yooks.adapter.groupie

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.yooks.R
import com.yvkalume.yooks.databinding.ItemGenreBinding
import com.yvkalume.model.presentation.RowGenre

class GenreItem(
    private val rowGenre: RowGenre,
    private val adapter: GroupAdapter<GroupieViewHolder>,
    private val viewPool: RecyclerView.RecycledViewPool
) : BindableItem<ItemGenreBinding>() {
    var genreTitle: String = rowGenre.title
    override fun bind(viewBinding: ItemGenreBinding, position: Int) {
        viewBinding.run {
            title.text = rowGenre.title
            recyclerView.adapter = adapter
        }
    }

    override fun createViewHolder(itemView: View): com.xwray.groupie.viewbinding.GroupieViewHolder<ItemGenreBinding> {
        return super.createViewHolder(itemView).also {
            it.binding.recyclerView.apply {
                setRecycledViewPool(viewPool)
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_genre

    override fun initializeViewBinding(view: View): ItemGenreBinding =
        ItemGenreBinding.bind(view)
}