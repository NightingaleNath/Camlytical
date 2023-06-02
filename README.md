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

Step 2: Add the library dependency
Open the module-level build.gradle file (usually located in the app directory).

Add the following dependency in the dependencies block:

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

```

Feel free to copy and paste this detailed installation guide into your README.md file. Make sure to replace `1.0.0` with the actual version of your library that you published on JitPack. Additionally, you can add any other necessary information or instructions specific to your library to provide a complete guide for users to install and use it in their Android projects.
