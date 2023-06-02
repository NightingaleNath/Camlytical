package com.codelytical.camlytical

import android.net.Uri

interface ImageCallback {
	fun onImageCaptured(imageUri: Uri)
	fun onImagePickCancelled()
}
