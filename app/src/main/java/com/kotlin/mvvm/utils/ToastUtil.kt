package com.kotlin.mvvm.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import android.view.LayoutInflater
import android.widget.TextView
import com.kotlin.mvvm.R

/**
 * Created by Waheed on 04,November,2019
 */

object ToastUtil {

    fun showCustomToast(context: Context, message: String) {
        try {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val layout = inflater.inflate(R.layout.toast_layout, null)
            val textView = layout.findViewById(R.id.tvToastMsgId) as TextView
            textView.setText(message)
            val toast = Toast(context)
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            toast.duration = Toast.LENGTH_SHORT
            toast.view = layout
            toast.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showNormalToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}