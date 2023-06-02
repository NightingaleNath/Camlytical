package com.codelytical.camlytical

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity(), ImageCallback {
	private val imagePicker: ImagePicker = ImagePickerImpl()
	private lateinit var imageView: ImageView

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val captureButton: Button = findViewById(R.id.captureButton)
		val selectImage: Button = findViewById(R.id.selectImage)
		val pickImageButton: Button = findViewById(R.id.pickImageButton)
		imageView = findViewById(R.id.imageView)

		captureButton.setOnClickListener {
			capturePhoto()
		}

		pickImageButton.setOnClickListener {
			pickImage()
		}

		selectImage.setOnClickListener {
			openImagePicker()
		}

	}

	private fun openImagePicker() {
		imagePicker.selectImage(this, this)
	}

	private fun capturePhoto() {
		imagePicker.capturePhoto(this, this)
	}

	private fun pickImage() {
		imagePicker.pickImage(this, this)
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		imagePicker.handleActivityResult(requestCode, resultCode, data)
	}

	override fun onImageCaptured(imageUri: Uri) {
		imageView.setImageURI(imageUri)
	}

	override fun onImagePickCancelled() {
		Toast.makeText(this, "Image capture or pick cancelled", Toast.LENGTH_SHORT).show()
	}

}