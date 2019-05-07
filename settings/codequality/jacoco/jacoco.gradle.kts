plugins {
    jacoco
}

val fileFilter = arrayOf(
        "**/*Parcel.class",
        "**/*\$CREATOR.class",
        "**/*Test*.*",
        "**/AutoValue_*.*",
        "**/*JavascriptBridge.class",
        "**/R.class",
        "**/R$*.class",
        "**/Manifest*.*",
        "android/**/*.*",
        "**/BuildConfig.*",
        "**/*\$ViewBinder*.*",
        "**/*\$ViewInjector*.*",
        "**/Lambda$*.class",
        "**/Lambda.class",
        "**/*Lambda.class",
        "**/*Lambda*.class",
        "**/*\$InjectAdapter.class",
        "**/*\$ModuleAdapter.class",
        "**/*\$ViewInjector*.class",
        "**/*_MembersInjector.class", //Dagger2 generated code
        "*/*_MembersInjector*.*", //Dagger2 generated code
        "**/*_*Factory*.*", //Dagger2 generated code
        "**/*Component*.*", //Dagger2 generated code
        "**/*Module*.*" //Dagger2 generated code
    )

val debugTree = fileTree("dir" to "$buildDir/intermediates/classes/debug", "excludes" to fileFilter)
val mainSrc = "${project.projectDir}/src/main/java"
val kotlinDebugTree = fileTree("dir" to "$buildDir/tmp/kotlin-classes/debug", "excludes" to fileFilter)

/*
jacoco {
    version = "0.8.3"
    toolVersion = "0.8.3"
}
*/

android {
    testOptions {
        animationsDisabled = true
        unitTests.apply {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
            all(KotlinClosure1<Any, Test>({
                (this as Test).also { testTask ->
                    testTask.extensions
                        .getByType(JacocoTaskExtension::class.java)
                        .isIncludeNoLocationClasses = true
                }
            }, this))
        }
        setExecution("ANDROID_TEST_ORCHESTRATOR")
    }
}

tasks.withType<JacocoReport> {
    group = "Reporting"
    description = "Generating Jacoco coverage reports"

    reports {
        xml.isEnabled = true
        html.isEnabled = true
        csv.isEnabled = true
    }
    sourceDirectories = files(mainSrc)
    classDirectories = files(debugTree, kotlinDebugTree)
    executionData = fileTree("dir" to buildDir, "includes" to arrayOf("**/*.exec", "**/*.ec"))
}