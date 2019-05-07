plugins {
    findbugs
}

val qualityConfigDir = "$project.rootDir/settings/codequality"
val sourceDir = "${project.projectDir}"

tasks.withType<FindBugs> {
    ignoreFailures = true
    effort = "max"
    reportLevel = "high"
    setExcludeFilter(File("$qualityConfigDir/findbugs/findbugs.xml"))

    classes = files("$sourceDir/build/intermediates/javac")
    classpath = files()
    source = fileTree("src/main/java")

    include("**/*.java")
    exclude("**/gen/**")

    reports {
        xml.isEnabled = false
        html.isEnabled = true
    }
}