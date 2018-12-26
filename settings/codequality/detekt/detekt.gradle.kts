import io.gitlab.arturbosch.detekt.detekt

plugins {
    id("io.gitlab.arturbosch.detekt")
}

val qualityConfigDir = "$project.rootDir/settings/codequality"
val sourceDir = "$project.rootDir"

detekt {
    toolVersion = "1.0.0.RC11"
    config = files("$qualityConfigDir/detekt/detekt.yml")
    input = files("src/main/kotlin")
    filters = ".*/resources/.*,.*/build/.*"

    baseline = file("$project.projectDir/reports/baseline.xml")
    reports {
        xml.enabled = true
        html.enabled = true
    }
}