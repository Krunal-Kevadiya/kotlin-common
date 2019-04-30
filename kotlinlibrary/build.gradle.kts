import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("com.github.dcendents.android-maven")
}
group = "com.kevadiyakrunalk"

//apply(from = "../settings/codequality/quality.gradle")

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
            Config.proguardList.forEach {
                proguardFile(file(it))
            }
        }
        getByName("debug") {
            isMinifyEnabled = false
            Config.proguardList.forEach {
                proguardFile(file(it))
            }
        }
    }
}

androidExtensions {
    configure(delegateClosureOf<AndroidExtensionsExtension> {
        isExperimental = true
    })
}

dependencies {
    compileOnlys {
        // Support Design
        +Depend.AndroidX.appcompat
        +Depend.AndroidX.material
        +Depend.AndroidX.recyclerview
        +Depend.AndroidX.exifInterface

        // Kotlin
        +Depend.Kotlin.stdlib
        +Depend.Kotlin.coroutinesCore

        // S3 Bucket
        +Depend.Amazon.s3
        +Depend.ThirdParty.commonsLogging

        // Location
        +Depend.Google.location

        // Socket IO
        +Depend.ThirdParty.socketIo

        // GSON
        +Depend.ThirdParty.gson

        // Retrofit
        +Depend.Retrofit.lib
        +Depend.Retrofit.adapterRx2
        +Depend.Retrofit.gson
        +Depend.OkHttp.lib
        +Depend.OkHttp.logging

        // Reactive
        +Depend.JavaxAndRx.android2
        +Depend.JavaxAndRx.java2

        //Livedata
        +Depend.Lifecycle.livedata
    }

    implementations {
        //Ssp, Sdp
        +Depend.Intuit.ssp
        +Depend.Intuit.sdp
        +"com.sdsmdg.harjot:materialshadows:1.2.5"
    }
}

// For jitPack
val sourcesJar = task("sourcesJar", Jar::class) {
    from(android.sourceSets["main"].java.srcDirs)
    classifier = "sources"
}

val javadoc = task("javadoc", Javadoc::class) {
    isFailOnError = false
    source = android.sourceSets["main"].java.sourceFiles
    classpath += project.files(android.bootClasspath.joinToString(File.pathSeparator))
    classpath += configurations.compile
}

val javadocJar = task("javadocJar", Jar::class) {
    dependsOn(javadoc)
    classifier = "javadoc"
    from(javadoc.destinationDir)
}

artifacts {
    add("archives", sourcesJar)
    add("archives", javadocJar)
}

/*
// For Bintry or jcenter
ext {
    PUBLISH_GROUP_ID = 'com.kevadiyakrunalk'
    PUBLISH_ARTIFACT_ID = 'kotlin-common'
    PUBLISH_VERSION = '1.0.0'
}

apply from: '../settings/maven/android-release-aar.gradle'
*/

