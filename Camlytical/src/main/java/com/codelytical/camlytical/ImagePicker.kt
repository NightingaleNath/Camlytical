package com.codelytical.camlytical

import android.app.Activity
import android.content.Intent
import com.codelytical.camlytical.ImageCallback

interface ImagePicker {
	fun getImageSources(activity: Activity): List<String>
	fun capturePhoto(activity: Activity, callback: ImageCallback)
	fun pickImage(activity: Activity, callback: ImageCallback)
	fun selectImage(activity: Activity, callback: ImageCallback)
	fun handleActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}
