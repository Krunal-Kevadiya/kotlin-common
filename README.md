Welcome to the kotlin-common wiki!

## Sdk version
[![](https://jitpack.io/v/Krunal-Kevadiya/kotlin-common.svg)](https://jitpack.io/#Krunal-Kevadiya/kotlin-common)

# Application Documentation
This SDK provided below feature with easy to used.

1. Button With Progress 
1. Credit Card EditText
1. Image Picker
1. Permission
1. Argument Pass by Activity or Fragment
1. Load More In RecyclerAdapter
1. Preference
1. Recycler Adapter
1. Resource
1. SnackBar
1. Spannable String
1. StatusBar AlertView
1. Validation
1. Skeleton
1. Location
1. Pull Down To Refresh
1. Retrofit Adapter

# How to integrated
To get a Git project into your build:

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Step 2. Add the dependency
```groovy
dependencies {
        implementation 'com.github.Krunal-Kevadiya:kotlin-common:x.y.z'
}
```
Also include below library based on used feature on your project:
```groovy
dependencies {
        // Support Design
        implementation("androidx.appcompat:appcompat:1.0.0")
        implementation("com.google.android.material:material:1.0.0-rc01")
        implementation("androidx.recyclerview:recyclerview:1.0.0")
        implementation("androidx.exifinterface:exifinterface:1.0.0")

        // Kotlin
        implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.10")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.1")

        // S3 Bucket
        implementation("com.amazonaws:aws-android-sdk-s3:2.7.5")
        implementation("commons-logging:commons-logging:1.2")

        // Location
        implementation("com.google.android.gms:play-services-location:16.0.0")

        // Socket IO
        implementation("io.socket:socket.io-client:1.0.0")

        // GSON
        implementation("com.google.code.gson:gson:2.8.5")

        // Retrofit
        implementation("com.squareup.retrofit2:retrofit:2.4.0")
        implementation("com.squareup.retrofit2:adapter-rxjava2:2.4.0")
        implementation("com.squareup.retrofit2:converter-gson:2.4.0")
        implementation("com.squareup.okhttp3:okhttp:3.11.0")
        implementation("com.squareup.okhttp3:logging-interceptor:3.11.0")

        // Reactive
        implementation("io.reactivex.rxjava2:rxandroid:2.1.0")
        implementation("io.reactivex.rxjava2:rxjava:2.2.2")

        //Livedata
        implementation("androidx.lifecycle:lifecycle-livedata:2.0.0-rc01")
    }
}
```
