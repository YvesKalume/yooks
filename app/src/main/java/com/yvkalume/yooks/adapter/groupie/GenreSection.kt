package com.yvkalume.yooks.adapter.groupie

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.yooks.R
import com.yvkalume.yooks.databinding.SectionGenreBinding

class GenreSection(
    private val adapter: GroupAdapter<GroupieViewHolder>,
    val viewPool: RecyclerView.RecycledViewPool
) : BindableItem<SectionGenreBinding>() {
    override fun bind(viewBinding: SectionGenreBinding, position: Int) {
        viewBinding.recyclerView.adapter = adapter
    }

    override fun createViewHolder(itemView: View): com.xwray.groupie.viewbinding.GroupieViewHolder<SectionGenreBinding> {
        return super.createViewHolder(itemView).also {
            it.binding.recyclerView.apply {
                setRecycledViewPool(viewPool)
                setHasFixedSize(true)
            }
        }
    }

    override fun getLayout(): Int = R.layout.section_genre

    override fun initializeViewBinding(view: View): SectionGenreBinding =
        SectionGenreBinding.bind(view)
}