import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Versions.Android.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Versions.Android.minSdkVersion)
        targetSdkVersion(Versions.Android.targetSdkVersion)
        vectorDrawables.useSupportLibrary = true
    }

    dataBinding {
        isEnabled = true
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false

        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }
}

androidExtensions {
    configure(delegateClosureOf<AndroidExtensionsExtension> {
        isExperimental = true
    })
}

dependencies {
    // Support Design
    compileOnly(Depend.AndroidX.appcompat)
    compileOnly(Depend.AndroidX.v4)
    compileOnly(Depend.AndroidX.material)
    compileOnly(Depend.AndroidX.recyclerview)

    // Kotlin
    compileOnly(Depend.Kotlin.stdlib)
    compileOnly(Depend.Kotlin.coroutinesCore)

    // S3 Bucket
    compileOnly(Depend.Amazon.s3)
    compileOnly(Depend.ThirdParty.commonsLogging)

    // Location
    compileOnly(Depend.Google.location)

    // Socket IO
    compileOnly(Depend.ThirdParty.socketIo)

    // GSON
    compileOnly(Depend.ThirdParty.gson)

    // Retrofit
    compileOnly(Depend.Retrofit.lib)
    compileOnly(Depend.Retrofit.adapterRx2)
    compileOnly(Depend.Retrofit.gson)
    compileOnly(Depend.OkHttp.lib)
    compileOnly(Depend.OkHttp.logging)

    // Reactive
    compileOnly(Depend.JavaxAndRx.android2)
    compileOnly(Depend.JavaxAndRx.java2)

    //Livedata
    compileOnly(Depend.Lifecycle.livedata)
}

/*
// For jitPack
task.sourcesJar(type: Jar) {
    from android.sourceSets.main.java.srcDirs
    classifier = 'sources'
}

task.javadoc(type: Javadoc) {
    failOnError  false
    source = android.sourceSets.main.java.sourceFiles
    classpath += project.files(android.getBootClasspath().join(File.pathSeparator))
    classpath += configurations.compile
}

task.javadocJar(type: Jar, dependsOn: javadoc) {
    classifier = 'javadoc'
    from javadoc.destinationDir
}

artifacts {
    archives sourcesJar
    archives javadocJar
}*/

/*
// For Bintry or jcenter
ext {
    PUBLISH_GROUP_ID = 'com.kevadiyakrunalk'
    PUBLISH_ARTIFACT_ID = 'kotlin-common'
    PUBLISH_VERSION = '1.0.0'
}

apply from: '../settings/maven/android-release-aar.gradle'*/
