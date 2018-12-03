plugins {
    checkstyle
}

val qualityConfigDir = "$project.rootDir/buildSrc/settings/codequality"
val sourceDir = "$project.rootDir"

checkstyle {
    configFile = file("config/checkstyle/sun_checks.xml")
    isIgnoreFailures = true
}
