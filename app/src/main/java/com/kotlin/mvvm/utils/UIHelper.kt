package com.kotlin.mvvm.utils

import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * Created by Waheed on 04,November,2019
 */

object UIHelper {

    /**
     * get display size
     *
     * @param context: Context
     *
     * @return: point(display size)
     */
    fun getDisplaySize(context: Context): Point {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager?
        val outSize = Point()
        windowManager?.defaultDisplay?.getSize(outSize)
        return outSize
    }

    /**
     * get display shorter side size
     *
     * @param context: Context
     *
     * @return size of display shorter side
     */
    fun getDisplayShorterSideSize(context: Context): Int {
        val outSize = getDisplaySize(context)
        return if (outSize.x < outSize.y) outSize.x else outSize.y
    }

    /**
     * calculate column of grid with cell width, will use when display image in grid view
     *
     * @param context:   Context
     * @param cellWidth: cell width
     *
     * @return: number of column
     */
    fun calcGridColumn(context: Context, cellWidth: Int): Int {
        val displaySize = getDisplaySize(context)
        val width = displaySize.x
        return width / cellWidth
    }

    fun getScreenOrientation(context: Context): Int {
        return if (getScreenWidth(context) < getScreenHeight(context)) {
            Configuration.ORIENTATION_PORTRAIT
        } else {
            Configuration.ORIENTATION_LANDSCAPE
        }
    }

    fun getScreenWidth(context: Context?): Int {
        return if (context == null) {
            0
        } else getDisplayMetrics(context).widthPixels
    }

    fun getScreenHeight(context: Context?): Int {
        return if (context == null) {
            0
        } else getDisplayMetrics(context).heightPixels
    }

    /**
     * Returns a valid DisplayMetrics object
     *
     * @param context valid context
     *
     * @return DisplayMetrics object
     */
    fun getDisplayMetrics(context: Context): DisplayMetrics {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager?
        val metrics = DisplayMetrics()
        windowManager?.defaultDisplay?.getMetrics(metrics)
        return metrics
    }

    /**
     * TODO Get status bar height
     * @param context
     */
    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources
            .getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}