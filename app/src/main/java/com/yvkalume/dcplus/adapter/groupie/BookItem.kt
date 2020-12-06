package com.yvkalume.dcplus.adapter.groupie

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.ItemBookBinding
import com.yvkalume.model.domain.Book
import com.yvkalume.util.setImageUrl

class BookItem(val book: Book) : BindableItem<ItemBookBinding>() {
    override fun bind(viewBinding: ItemBookBinding, position: Int) {
        viewBinding.run {
            title.text = book.title
            appCompatImageView.setImageUrl(book.imageUrl)
        }
    }

    override fun getLayout(): Int = R.layout.item_book

    override fun initializeViewBinding(view: View): ItemBookBinding =
        ItemBookBinding.bind(view)
}