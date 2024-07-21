package com.fintech.demo.presentation.utils

import android.view.View
import android.view.animation.TranslateAnimation

const val BOTTOM_TO_TOP_DURATION = 1500L
fun View.animateFromBottomToTop() {
    this.visibility = View.VISIBLE
    val animate = TranslateAnimation(
        0F,
        0F,
        this.height.toFloat(),
        0F
    )
    animate.duration = 500
    animate.fillAfter = true
    this.startAnimation(animate)
}