package com.yvkalume.dcplus.ui.preview

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.yvkalume.dcplus.R
import com.yvkalume.dcplus.databinding.FragmentPreviewBinding
import com.yvkalume.dcplus.getEpisodes
import com.yvkalume.dcplus.adapter.groupie.BdHorizontalItem
import com.yvkalume.model.entity.Episode
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.fragment_preview.*


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