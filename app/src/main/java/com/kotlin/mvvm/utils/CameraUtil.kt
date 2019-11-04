package com.kotlin.mvvm.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Waheed on 04,November,2019
 */

object CameraUtil {

    fun takePhoto(activity: Activity, requestCode: Int): String? {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        var photoFile: File? = null
        if (takePictureIntent.resolveActivity(activity.packageManager) != null) {
            // Create the File where the photo should go
            try {
                photoFile = createImageFile()
            } catch (ignored: IOException) {
                ignored.printStackTrace()
            }
            if (photoFile != null) {
                val photoUri = getUriFromFile(activity, photoFile)
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                activity.startActivityForResult(takePictureIntent, requestCode)
            }
        }

        return photoFile?.absolutePath
    }


    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = Environment.getExternalStorageDirectory()
        val tempDir = File(storageDir.absolutePath + "/.temp/")
        if (!tempDir.exists()) {
            tempDir.mkdirs()
        }

        return File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            storageDir      /* directory */
        )
    }

    private fun getUriFromFile(context: Context, file: File) =
        FileProvider.getUriForFile(context, "in.ceeq.define.fileprovider", file)

    fun getUriFromFilePath(context: Context, filePath: String): Uri =
        FileProvider.getUriForFile(context, "in.ceeq.define.fileprovider", File(filePath))
}