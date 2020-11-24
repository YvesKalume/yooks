package com.yvkalume.dcplus.groupie

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.ItemGenreBinding
import com.yvkalume.model.entity.Genre

class GenreItem(
    private val genre: Genre,
    private val adapter: GroupAdapter<GroupieViewHolder>,
    private val viewPool: RecyclerView.RecycledViewPool
) : BindableItem<ItemGenreBinding>() {
    var genreTitle: String = genre.title
    override fun bind(viewBinding: ItemGenreBinding, position: Int) {
        viewBinding.run {
            title.text = genre.title
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