package com.codelytical.camlytical.librarycall

import android.app.Activity
import android.content.Intent
import com.codelytical.camlytical.view.HandGestureActivity

class HandGestureLibrary(private val activity: Activity) {
    private var callback: ((String?) -> Unit)? = null

    fun handGestureCamera(
        captureDelaySeconds: Long,
        validCategories: List<String>,
        callback: (String?) -> Unit
    ) {
        this.callback = callback

        val intent = Intent(activity, HandGestureActivity::class.java)
        intent.putExtra("captureDelaySeconds", captureDelaySeconds)
        intent.putStringArrayListExtra("validCategories", ArrayList(validCategories))
        activity.startActivityForResult(intent, GESTURE_CAMERA_REQUEST_CODE)
    }

    fun handleHandDetectedResult(resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val detectedHandGesture = data?.getStringExtra("gestureCategory")
            val categoryName = if (!detectedHandGesture.isNullOrEmpty()) detectedHandGesture else null
            callback?.invoke(categoryName)
        }
    }
}