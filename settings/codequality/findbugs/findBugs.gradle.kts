plugins {
    findbugs
}

val qualityConfigDir = "$project.rootDir/settings/codequality"
val sourceDir = "$project.rootDir"

tasks.withType<FindBugs> {
    ignoreFailures = true
    effort = "max"
    reportLevel = "high"
    setExcludeFilter(File("$qualityConfigDir/findbugs/findbugs.xml"))

    classes = files("$sourceDir/app/build/intermediates/classes", "$sourceDir/kotlinlibrary/build/intermediates/classes")
    classpath = files()
    source = fileTree("src/main/java")

    include("**/*.java")
    exclude("**/gen/**")

    reports {
        xml.isEnabled = true
        html.isEnabled = true
    }
}