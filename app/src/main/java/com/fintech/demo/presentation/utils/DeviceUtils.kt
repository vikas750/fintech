package com.fintech.demo.presentation.utils

import android.content.Context

object DeviceUtils {

    /**
     * To get device width.
     *
     * @return Device width
     */
    fun getDeviceWidth(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.widthPixels
    }

    /**
     * To get device heigth.
     *
     * @return Device heigth
     */
    fun getDeviceHeight(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.heightPixels
    }
}