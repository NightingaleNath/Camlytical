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
