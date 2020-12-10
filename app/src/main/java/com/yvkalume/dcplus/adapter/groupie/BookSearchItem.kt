package com.yvkalume.dcplus.adapter.groupie

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.ItemBookSearchBinding
import com.yvkalume.model.domain.Book
import com.yvkalume.util.setImageUrl

class BookSearchItem(val book: Book) : BindableItem<ItemBookSearchBinding>() {
    override fun bind(viewBinding: ItemBookSearchBinding, position: Int) {
        viewBinding.run {
            title.text = book.title
            appCompatImageView.setImageUrl(book.imageUrl)
        }
    }

    override fun getLayout(): Int = R.layout.item_book_search

    override fun initializeViewBinding(view: View): ItemBookSearchBinding =
        ItemBookSearchBinding.bind(view)
}