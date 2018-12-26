import com.diffplug.gradle.spotless.SpotlessTask

plugins {
    id("com.diffplug.gradle.spotless")
}
val qualityConfigDir = "$project.rootDir/settings/codequality"
val sourceDir = "$project.rootDir"

tasks.withType<Spotless> {
    java {
        target = '**/*.java'
        googleJavaFormat()
        licenseHeaderFile "$qualityConfigDir/spotless/copyright.java"
    }
    kotlin {
        target "**/*.kt"
        ktlint()
        licenseHeaderFile "$qualityConfigDir/spotless/copyright.kt"
    }
}