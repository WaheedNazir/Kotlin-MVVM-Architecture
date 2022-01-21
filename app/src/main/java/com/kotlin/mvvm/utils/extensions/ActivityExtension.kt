package com.kotlin.mvvm.utils.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import com.kotlin.mvvm.utils.ToastUtil

/**
 * Created by Waheed on 04,November,2019
 */

fun Activity.startActivity(clazz: Class<*>) {
    val intent = Intent(this, clazz)
    startActivity(intent)
}

fun Activity.startActivity(clazz: Class<*>, bundle: Bundle) {
    val intent = Intent(this, clazz)
    intent.putExtras(bundle)
    startActivity(intent)
}

fun Activity.startActivityForResult(clazz: Class<*>, requestCode: Int) {
    val intent = Intent(this, clazz)
    startActivityForResult(intent, requestCode)
}

fun Activity.startActivityForResult(clazz: Class<*>, requestCode: Int, bundle: Bundle) {
    val intent = Intent(this, clazz)
    intent.putExtras(bundle)
    startActivityForResult(intent, requestCode)
}

fun Activity.startActivityNewTask(clazz: Class<*>) {
    val intent = Intent(this, clazz)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.customToast(message: String) {
    ToastUtil.showCustomToast(this, message)
}

@Suppress("DEPRECATION")
fun Activity.isConnectedInternet(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return if (connectivityManager != null) {
        val networkInfo = connectivityManager.activeNetworkInfo
        networkInfo != null && networkInfo.isConnected
    } else {
        false
    }
}