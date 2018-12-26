import java.io.File

object Config {

    object AppType {
        val app = "app"
    }

    object Application {
        val propertyProject = "project"
        val flavorDimention = "dimention"
        val applicationId = "com.kotlincommon.sample"
    }

    object BuildFurniture {
        val ENABLE_CRASHLYTICS = "ENABLE_CRASHLYTICS"
    }

    object Flavors {
        val production = "production"
        val qa = "qa"
        val development = "development"
    }

    val repositoryList: List<String> = listOf(
        "https://oss.sonatype.org/content/repositories/snapshots",
        "http://dl.bintray.com/piasy/maven",
        "https://maven.google.com",
        "https://www.jitpack.io",
        "https://maven.fabric.io/public",
        "https://plugins.gradle.org/m2"
    )

    val progaurdPath = "${System.getProperty("user.dir")}/settings/proguard"
    val proguardList: List<String> = listOf(
        "$progaurdPath/proguard-architecture-components.pro",
        "$progaurdPath/proguard-crashlytics-2.pro",
        "$progaurdPath/proguard-gson.pro",
        "$progaurdPath/proguard-project.pro",
        "$progaurdPath/proguard-rxjava-rxandroid.pro",
        "$progaurdPath/proguard-square-okhttp3.pro",
        "$progaurdPath/proguard-square-retrofit.pro",
        "$progaurdPath/proguard-support-design.pro",
        "$progaurdPath/proguard-support-v4.pro",
        "$progaurdPath/proguard-picasso.pro"
    )
}