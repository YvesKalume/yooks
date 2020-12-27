package com.yvkalume.yooks.util

import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.recyclerview.widget.RecyclerView
import com.yvkalume.yooks.R

fun View.runAnimationUp() {
    val controller = AnimationUtils.loadAnimation(context, R.anim.fab_scale_up)
    animation = controller
}

fun RecyclerView.runLayoutAnimation() {
    val controller: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down)
    layoutAnimation = controller
    scheduleLayoutAnimation()
}