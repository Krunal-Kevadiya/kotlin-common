import java.net.URI

buildscript {
    addRepo(repositories)

    dependencies {
        classpath(Depend.BuildPlugins.androidPlugin)
        classpath(Depend.BuildPlugins.kotlinPlugin)
        classpath(Depend.BuildPlugins.sonarqubePlugin)
        classpath(Depend.BuildPlugins.detektPlugin)
        classpath(Depend.BuildPlugins.spotlessPlugin)
    }
}

allprojects {
    addRepo(repositories)
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}

//apply(from = "./settings/codequality/detekt/detekt.gradle")
//apply(from = "./settings/codequality/sonarqube/sonarqubeProject.gradle")


