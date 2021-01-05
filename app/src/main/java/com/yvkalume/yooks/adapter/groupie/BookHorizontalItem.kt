package com.yvkalume.yooks.adapter.groupie

import android.view.View
import com.google.firebase.storage.FirebaseStorage
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.yooks.R
import com.yvkalume.yooks.databinding.ItemBookHorizontalBinding
import com.yvkalume.model.domain.Book
import com.yvkalume.yooks.util.setImageUrl

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