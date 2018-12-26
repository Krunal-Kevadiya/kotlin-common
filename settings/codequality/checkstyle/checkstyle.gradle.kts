plugins {
    checkstyle
}

val qualityConfigDir = "${project.rootDir}/settings/codequality"
val sourceDir = "${project.rootDir}"

tasks.withType<Checkstyle> {
    isShowViolations = true
    isIgnoreFailures = false
    configFile = file("$qualityConfigDir/checkstyle/checkstyle.xml")
    configProperties = mapOf("suppressionFile" to project(":").file("$qualityConfigDir/checkstyle/suppressions.xml"))

    source = fileTree("src")
    include("**/*.java")
    exclude("**/model/**", "**/AppLogger.java", "**/gen/**", "**/test/**", "**/androidTest/**", "**/R.java", "**/BuildConfig.java")
    //classpath = files()
    classpath = files("$sourceDir/app", "$sourceDir/kotlinlibrary")

    reports {
        xml.isEnabled = true
        html.isEnabled = true
    }
}