package com.yvkalume.dcplus.ui.preview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.FragmentPreviewBinding
import jp.wasabeef.blurry.Blurry


class PreviewFragment : Fragment(R.layout.fragment_preview) {
    private val binding by lazy { FragmentPreviewBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Blurry.with(context).from(binding.roundedImageView2.drawable.toBitmap()).into(binding.backgroundImg)

    }

}