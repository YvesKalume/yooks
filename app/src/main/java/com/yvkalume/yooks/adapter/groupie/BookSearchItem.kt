package com.yvkalume.yooks.adapter.groupie

import android.view.View
import com.google.firebase.storage.FirebaseStorage
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.yooks.R
import com.yvkalume.yooks.databinding.ItemBookSearchBinding
import com.yvkalume.model.domain.Book
import com.yvkalume.util.setImageUrl

class BookSearchItem(val book: Book) : BindableItem<ItemBookSearchBinding>() {
    override fun bind(viewBinding: ItemBookSearchBinding, position: Int) {
        viewBinding.run {
            title.text = book.title
            FirebaseStorage.getInstance().getReferenceFromUrl(book.imageUrl).downloadUrl.addOnSuccessListener {
                appCompatImageView.setImageUrl(it.toString())
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_book_search

    override fun initializeViewBinding(view: View): ItemBookSearchBinding =
        ItemBookSearchBinding.bind(view)
}