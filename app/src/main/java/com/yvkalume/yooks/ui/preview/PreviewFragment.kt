package com.yvkalume.yooks.ui.preview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.airbnb.mvrx.*
import com.google.firebase.storage.FirebaseStorage
import com.pdftron.pdf.config.ViewerConfig
import com.pdftron.pdf.controls.DocumentActivity
import com.yvkalume.yooks.R
import com.yvkalume.yooks.databinding.FragmentPreviewBinding
import com.yvkalume.model.domain.Book
import com.yvkalume.yooks.util.setImageUrl


class PreviewFragment : Fragment(R.layout.fragment_preview), MavericksView {
    private val viewModel: PreviewViewModel by activityViewModel()
    private val binding: FragmentPreviewBinding by viewBinding()
    private val args: PreviewFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRead.setOnClickListener {
            read()
        }
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
            FirebaseStorage.getInstance().getReferenceFromUrl(book.imageUrl).downloadUrl.addOnSuccessListener {
                bookImg.setImageUrl(it.toString())
            }
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
        binding.loader.isVisible = it.previewData is Loading
        when(it.previewData) {
            is Loading -> Unit
            is Success -> bindData(it.previewData.invoke().book)
            is Fail -> Unit
        }
    }


    fun read() {
//        Todo: put book url
        FirebaseStorage.getInstance().getReferenceFromUrl(args.book.pdfUrl).downloadUrl.addOnSuccessListener {
            val fileLink = it
            DocumentActivity.openDocument(requireContext(), fileLink, getDocumentConfig())
        }
    }

    private fun getDocumentConfig(): ViewerConfig {

        return ViewerConfig.Builder()
            .fullscreenModeEnabled(false)
            .multiTabEnabled(false)
            .documentEditingEnabled(false)
            .longPressQuickMenuEnabled(true)
            .showPageNumberIndicator(true)
            .showBottomNavBar(true)
            .showThumbnailView(true)
            .showBookmarksView(false)
            .toolbarTitle(args.book.title)
            .showSearchView(true)
            .showShareOption(false)
            .showDocumentSettingsOption(false)
            .showAnnotationToolbarOption(false)
            .showOpenFileOption(false)
            .showOpenUrlOption(false)
            .showEditPagesOption(false)
            .showPrintOption(false)
            .showCloseTabOption(true)
            .showAnnotationsList(false)
            .showOutlineList(false)
            .showSaveCopyOption(false)
            .showUserBookmarksList(false)
            .openUrlCachePath(requireActivity().cacheDir.absolutePath)
            .build()
    }

}