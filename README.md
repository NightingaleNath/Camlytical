# Camlytical
simple camera library

## Installation

To use the Camlytical library in your Android project, follow the steps below:

### Step 1: Add the JitPack repository

Open your project's `build.gradle` file. If you have multiple modules, make sure to update the `build.gradle` file of the module where you want to use the library.

Add the following repository to the `repositories` block:

```groovy
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2: Add the library dependency
Open the module-level build.gradle file (usually located in the app directory).

Add the following dependency in the dependencies block:

```
dependencies {
    // other dependencies

    implementation 'com.github.NkrumahNath:Camlytical:1.0.0'
}
```

This line adds the Camlytical library to your project.

Step 3: Sync your project
Sync your project with the updated Gradle configuration. Gradle will download the library and make it available for your project.

You can sync your project by clicking the "Sync Now" button that appears in the Android Studio toolbar or by selecting "File" > "Sync Project with Gradle Files" from the menu.

Step 4: Start using the Camlytical library
Now that you have successfully added the Camlytical library to your project, you can start using its features in your code.

Please refer to the documentation or sample code provided with the library to learn how to use its functionality in your app.

That's it! You have successfully installed the Camlytical library in your Android project. Enjoy using it in your app!

****************************************************************************************************************************
Sample Example
Here's a simple example of how to use the Camlytical library to capture an image:

```
import com.codelytical.cameralytical.ImageCallback
import com.codelytical.cameralytical.ImagePickerImpl
```

In your activity's XML layout file, include the necessary views:
```
<Button
    android:id="@+id/captureButton"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Capture Photo" />

<Button
    android:id="@+id/pickImageButton"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Pick Image from Gallery" />

<Button
    android:id="@+id/selectImage"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Select Image Source" />

<ImageView
    android:id="@+id/imageView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />

```
In your activity class, implement the ImageCallback interface and initialize the ImagePicker:
```
class MainActivity : AppCompatActivity(), ImageCallback {
    private val imagePicker: ImagePicker = ImagePickerImpl()
    private lateinit var imageView: ImageView

    // ...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        val captureButton: Button = findViewById(R.id.captureButton)
        val selectImage: Button = findViewById(R.id.selectImage)
        val pickImageButton: Button = findViewById(R.id.pickImageButton)
        imageView = findViewById(R.id.imageView)

        // ...
    }

    // ...
}
```
Use the ImagePicker methods to handle image selection and capture:
```
private fun openImageSelector() {
    imagePicker.selectImage(this, this)
}

private fun capturePhoto() {
    imagePicker.capturePhoto(this, this)
}

private fun pickImage() {
    imagePicker.pickImage(this, this)
}

```
Implement the ImageCallback methods to handle the captured or picked image:
```
override fun onImageCaptured(imageUri: Uri) {
    imageView.setImageURI(imageUri)
}

override fun onImagePickCancelled() {
    Toast.makeText(this, "Image capture or pick cancelled", Toast.LENGTH_SHORT).show()
}

```

**Complete sample Code**
```
class MainActivity : AppCompatActivity(), ImageCallback {
    private val imagePicker: ImagePicker = ImagePickerImpl()
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        val captureButton: Button = findViewById(R.id.captureButton)
        val selectImage: Button = findViewById(R.id.selectImage)
        val pickImageButton: Button = findViewById(R.id.pickImageButton)
        imageView = findViewById(R.id.imageView)

        // Set click listeners
        captureButton.setOnClickListener {
            // Call the method to capture a photo
            capturePhoto()
        }

        pickImageButton.setOnClickListener {
            // Call the method to pick an image from gallery
            pickImage()
        }

        selectImage.setOnClickListener {
            // Call the method to open the image selector
            openImageSelector()
        }
    }

    // Open the image selector
    private fun openImageSelector() {
        imagePicker.selectImage(this, this)
    }

    // Capture a photo
    private fun capturePhoto() {
        imagePicker.capturePhoto(this, this)
    }

    // Pick an image from gallery
    private fun pickImage() {
        imagePicker.pickImage(this, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Pass the activity result to the image picker
        imagePicker.handleActivityResult(requestCode, resultCode, data)
    }

    override fun onImageCaptured(imageUri: Uri) {
        // Set the captured image URI to the image view
        imageView.setImageURI(imageUri)
    }

    override fun onImagePickCancelled() {
        // Display a toast message when image capture or pick is cancelled
        Toast.makeText(this, "Image capture or pick cancelled", Toast.LENGTH_SHORT).show()
    }
}

```

**NB:**

Feel free to copy and paste this detailed installation guide into your README.md file. Make sure to replace `1.0.0` with the actual version of your library that you published on JitPack. Additionally, you can add any other necessary information or instructions specific to your library to provide a complete guide for users to install and use it in their Android projects.
