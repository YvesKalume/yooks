package com.yvkalume.dcplus.adapter.groupie

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.ItemBookHorizontalBinding
import com.yvkalume.model.domain.Book
import com.yvkalume.util.setImageUrl

class BookHorizontalItem(val book: Book) : BindableItem<ItemBookHorizontalBinding>() {
    override fun bind(viewBinding: ItemBookHorizontalBinding, position: Int) {
        viewBinding.run {
            title.text = book.title
            roundedImageView.setImageUrl(book.imageUrl)
        }
    }

    override fun getLayout(): Int = R.layout.item_book_horizontal

    override fun initializeViewBinding(view: View): ItemBookHorizontalBinding =
        ItemBookHorizontalBinding.bind(view)
}