plugins {
    pmd
}

val qualityConfigDir = "$project.rootDir/settings/codequality"
val sourceDir = "$project.rootDir"

tasks.withType<Pmd> {
    ruleSetFiles = files("$qualityConfigDir/pmd/ruleset.xml")
    ignoreFailures = false
    ruleSets = listOf()

    source = fileTree("src/main/java")
    include("**/*.java")
    exclude("**/gen/**", "**/debug/**", "**/model/**")

    reports {
        xml.isEnabled = true
        html.isEnabled = true
    }
}