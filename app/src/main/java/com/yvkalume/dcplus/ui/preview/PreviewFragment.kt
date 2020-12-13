package com.yvkalume.dcplus.ui.preview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.navArgs
import com.airbnb.mvrx.*
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.FragmentPreviewBinding
import com.yvkalume.model.domain.Book
import com.yvkalume.util.setImageUrl


class PreviewFragment : Fragment(R.layout.fragment_preview), MavericksView {
    private val viewModel: PreviewViewModel by activityViewModel()
    private val binding: FragmentPreviewBinding by viewBinding()
    private val args: PreviewFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Blurry.with(context).from(binding.bookImg.drawable.toBitmap()).into(binding.backgroundImg)
    }

    override fun onStart() {
        super.onStart()
        viewModel.load(args.book.uid)
        viewModel.onAsync(
            asyncProp = PreviewViewState::isFavorite,
            onSuccess = {
                setFavoriteBtn(it)
            }
        )
    }

    private fun bindData(book: Book) {
        binding.run {
            bookImg.setImageUrl(book.imageUrl)
            bookTitle.text = book.title
        }
    }

    private fun setFavoriteBtn(value: Boolean) {
        when(value) {
            true -> {
                binding.btnAddFavorite.apply {
                    setImageResource(R.drawable.ic_favorite_full)
                    setOnClickListener {
                        viewModel.removeBookFromFavorite(args.book.uid)
                    }
                }
            }
            false -> {
                binding.btnAddFavorite.apply {
                    setImageResource(R.drawable.ic_favorite)
                    setOnClickListener {
                        viewModel.addToFavorite(args.book)
                    }
                }
            }
        }
    }

    override fun invalidate() = withState(viewModel) {
        when(it.previewData) {
            is Loading -> Unit
            is Success -> bindData(it.previewData.invoke().book)
            is Fail -> Unit
        }
    }

}