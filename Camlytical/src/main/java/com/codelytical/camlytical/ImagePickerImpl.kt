package com.codelytical.camlytical

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.codelytical.camlytical.ImageCallback
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ImagePickerImpl : ImagePicker {

	private lateinit var activity: Activity
	private lateinit var imageCallback: ImageCallback
	private lateinit var currentPhotoPath: String

	@SuppressLint("QueryPermissionsNeeded")
	override fun getImageSources(activity: Activity): List<String> {
		val sources = mutableListOf<String>()

		val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
		if (cameraIntent.resolveActivity(activity.packageManager) != null) {
			sources.add("Camera")
		}

		val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
		if (galleryIntent.resolveActivity(activity.packageManager) != null) {
			sources.add("Gallery")
		}

		sources.add("Cancel")

		return sources
	}


	override fun selectImage(activity: Activity, callback: ImageCallback) {
		this.activity = activity
		this.imageCallback = callback

		val sources = getImageSources(activity).toTypedArray()

		AlertDialog.Builder(activity)
			.setTitle("Choose Image Source")
			.setItems(sources) { dialog, which ->
				when (sources[which]) {
					"Camera" -> capturePhoto(activity, callback)
					"Gallery" -> pickImage(activity, callback)
					"Cancel" -> {
						dialog.dismiss()
						callback.onImagePickCancelled()
					}
				}
			}
			.show()
	}

	override fun pickImage(activity: Activity, callback: ImageCallback) {
		this.activity = activity
		this.imageCallback = callback

		val pickImageIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
		activity.startActivityForResult(pickImageIntent, REQUEST_CODE_PICK_IMAGE)
	}

	@SuppressLint("QueryPermissionsNeeded")
	override fun capturePhoto(activity: Activity, callback: ImageCallback) {
		this.activity = activity
		this.imageCallback = callback

		if (checkCameraPermission()) {
			startCapturePhoto(activity, callback)
		} else {
			requestCameraPermission()
		}
	}

	@SuppressLint("QueryPermissionsNeeded")
	private fun startCapturePhoto(activity: Activity, callback: ImageCallback) {
		val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
		if (takePictureIntent.resolveActivity(activity.packageManager) != null) {
			val photoFile: File? = try {
				createImageFile()
			} catch (ex: Exception) {
				ex.printStackTrace()
				null
			}

			if (photoFile != null) {
				val photoUri: Uri = FileProvider.getUriForFile(
					activity,
					activity.packageName + ".fileprovider",
					photoFile
				)
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
				// Start the camera activity using startActivityForResult
				activity.startActivityForResult(takePictureIntent, REQUEST_CODE_CAPTURE_PHOTO)
			} else {
				showErrorMessage("Failed to create image file")
			}
		} else {
			showErrorMessage("No camera app available")
			callback.onImagePickCancelled()
		}
	}

	override fun handleActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		if (resultCode == Activity.RESULT_OK) {
			when (requestCode) {
				REQUEST_CODE_CAPTURE_PHOTO -> {
					val imageUri = Uri.parse(currentPhotoPath)
					imageCallback.onImageCaptured(imageUri)
				}
				REQUEST_CODE_PICK_IMAGE -> {
					val imageUri = data?.data
					if (imageUri != null) {
						imageCallback.onImageCaptured(imageUri)
					} else {
						imageCallback.onImagePickCancelled()
					}
				}
				REQUEST_CODE_CAMERA_PERMISSION -> {
					if (checkCameraPermission()) {
						// Permission granted, start the capture photo process
						startCapturePhoto(activity, imageCallback)
					} else {
						// Permission denied, handle accordingly
						imageCallback.onImagePickCancelled()
					}
				}
			}
		} else {
			imageCallback.onImagePickCancelled()
		}
	}

	private fun checkCameraPermission(): Boolean {
		val permission = Manifest.permission.CAMERA
		return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
	}

	private fun requestCameraPermission() {
		val permission = Manifest.permission.CAMERA
		val requestCode = REQUEST_CODE_CAMERA_PERMISSION
		ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
	}

	private fun createImageFile(): File? {
		val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
		val storageDir: File? = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
		if (storageDir != null && storageDir.exists()) {
			val imageFile = File(storageDir, "JPEG_${timeStamp}.jpg")
			currentPhotoPath = imageFile.absolutePath
			return imageFile
		}
		return null
	}

	private fun showErrorMessage(message: String) {
		Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
	}

	companion object {
		private const val REQUEST_CODE_CAPTURE_PHOTO = 100
		private const val REQUEST_CODE_PICK_IMAGE = 101
		private const val REQUEST_CODE_CAMERA_PERMISSION = 200
	}
}
