package com.yvkalume.yooks.util

import android.view.View
import android.view.animation.AnimationUtils
import com.yvkalume.yooks.R

fun View.runAnimationUp() {
    val controller = AnimationUtils.loadAnimation(context, R.anim.fab_scale_up)
    animation = controller
}