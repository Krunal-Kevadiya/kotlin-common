plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Versions.Android.compileSdkVersion)

    defaultConfig {
        applicationId = ""
        minSdkVersion(Versions.Android.minSdkVersion)
        targetSdkVersion(Versions.Android.targetSdkVersion)
        manifestPlaceholders = mapOf("crashlyticsKey" to "krunal")
        vectorDrawables.useSupportLibrary = true
    }

    dataBinding {
        isEnabled = true
    }

    androidExtensions {
        isExperimental = true
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

dependencies {
    implementation(Depend.AndroidX.appcompat)
    implementation(Depend.AndroidX.material)
    implementation(Depend.AndroidX.recyclerview)
    implementation(Depend.AndroidX.swipereFreshlayout)

    implementation(Depend.Constraint.lib)

    implementation(Depend.Kotlin.stdlib)

    implementation(Depend.Intuit.sdp)
    implementation(Depend.Intuit.ssp)

    implementation(Depend.ThirdParty.gson)

    implementation(Depend.Google.location)

    //implementation('com.github.Krunal-Kevadiya:kotlin-common:1.0.1")
    implementation(project(":kotlinlibrary"))
}
/*
def readVersionName(def buildType = "buildVariant") {
    def version = readVersionFile("app", buildType)
    return "${version['VERSION_MAJOR']}.${version['VERSION_MINOR']}.${version['VERSION_PATCH']}(${version['VERSION_CODE']})"
}

def readVersionCode(def buildType = "buildVariant") {
    def version = readVersionFile("app", buildType)
    def build = version['VERSION_CODE'] as int
    return build
}

def readCrashlyticsKey() {
    def version = readVersionFile("app", flavorFiles.project)
    return "${version['FABRIC_API_TOKEN']}"
}

def readApplicationId() {
    def version = readVersionFile("app", flavorFiles.project)
    return "${version['APPLICATION_ID']}"
}*/
