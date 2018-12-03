import java.net.URI

buildscript {
    apply(from = "./buildSrc/repositories.gradle.kts")

    repositories {
        google()
        jcenter()
        mavenCentral()
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
        maven { url = uri("http://dl.bintray.com/piasy/maven") }
        maven { url = uri("https://maven.google.com") }
        maven { url = uri("https://www.jitpack.io") }
        maven { url = uri("https://maven.fabric.io/public") }
        maven { url = uri("https://plugins.gradle.org/m2") }
    }
    dependencies {
        classpath(Depend.BuildPlugins.androidPlugin)
        classpath(Depend.BuildPlugins.kotlinPlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
        maven { url = uri("http://dl.bintray.com/piasy/maven") }
        maven { url = uri("https://maven.google.com") }
        maven { url = uri("https://www.jitpack.io") }
        maven { url = uri("https://maven.fabric.io/public") }
        maven { url = uri("https://plugins.gradle.org/m2") }
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
