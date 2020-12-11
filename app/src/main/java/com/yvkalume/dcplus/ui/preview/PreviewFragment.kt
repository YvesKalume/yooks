package com.yvkalume.dcplus.ui.preview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.airbnb.mvrx.*
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.FragmentPreviewBinding
import com.yvkalume.model.domain.Book
import com.yvkalume.util.setImageUrl
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.fragment_preview.*


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
    }

    private fun bindData(book: Book) {
        binding.run {
            bookImg.setImageUrl(book.imageUrl)
            bookTitle.text = book.title
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