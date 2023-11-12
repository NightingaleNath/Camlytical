package com.codelytical.camlytical

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.codelytical.camlytical.librarycall.GESTURE_CAMERA_REQUEST_CODE
import com.codelytical.camlytical.librarycall.HandGestureLibrary

class MainActivity : AppCompatActivity() {
	private lateinit var imageView: ImageView
	private var handGestureLibrary: HandGestureLibrary? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		handGestureLibrary = HandGestureLibrary(this)

		val captureButton: Button = findViewById(R.id.captureButton)
		val selectImage: Button = findViewById(R.id.selectImage)
		val pickImageButton: Button = findViewById(R.id.pickImageButton)
		imageView = findViewById(R.id.imageView)

		captureButton.setOnClickListener {
			val validCategories = listOf("Open_Palm", "Thumb_Up", "Closed_Fist")

			handGestureLibrary?.handGestureCamera(captureDelaySeconds = 2000L, validCategories) { categoryName ->
				if (categoryName != null) {
					showToast(categoryName)
					Log.e("TAG", "categoryName $categoryName")
				} else {
					Log.d("TAG", "onCreate: Nothing")
				}
			}

		}
	}

	private fun showToast(message: String) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
	}

	@Deprecated("Deprecated in Java")
	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (requestCode == GESTURE_CAMERA_REQUEST_CODE) {
			handGestureLibrary?.handleHandDetectedResult(resultCode, data)
		}
		//imagePicker.handleActivityResult(requestCode, resultCode, data)
	}


}