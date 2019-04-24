import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.android.builder.model.ProductFlavor

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

//apply(from = "../settings/codequality/quality.gradle")

android {
    compileSdkVersion(Versions.Android.compileSdkVersion)

    defaultConfig {
        readAppSetting(Config.AppType.app, project) { id, key ->
            applicationId = id
            manifestPlaceholders = mapOf("crashlyticsKey" to key)
        }
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

    flavorDimensions(Config.Application.flavorDimention)
    productFlavors {
        create(Config.Flavors.production) {
            readVersion(Config.AppType.app, Config.Flavors.production, project) { name, code ->
                versionName = name
                versionCode = code
            }
        }
        create(Config.Flavors.qa) {
            readVersion(Config.AppType.app, Config.Flavors.qa, project) { name, code ->
                versionName = name
                versionCode = code
            }
        }
        create(Config.Flavors.development) {
            readVersion(Config.AppType.app, Config.Flavors.development, project) { name, code ->
                versionName = name
                versionCode = code
            }
        }
    }
}

dependencies {
    implementations {
        // Support Design
        +Depend.AndroidX.appcompat
        +Depend.AndroidX.material
        +Depend.AndroidX.recyclerview
        +Depend.AndroidX.swipereFreshlayout
        +Depend.Constraint.lib
        +Depend.AndroidX.exifInterface

        //Kotlin
        +Depend.Kotlin.stdlib

        //Margin, Padding and fontsize
        +Depend.Intuit.sdp
        +Depend.Intuit.ssp

        //Json parsing
        +Depend.ThirdParty.gson

        //Location
        +Depend.Google.location
    }
    //implementation("com.github.Krunal-Kevadiya:kotlin-common:1.1.0")
    implementation(project(":kotlinlibrary"))
}