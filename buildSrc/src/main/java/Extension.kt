import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.internal.impldep.com.esotericsoftware.minlog.Log
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project
import org.gradle.nativeplatform.BuildType
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.net.URI
import java.util.*

fun addRepo(repositories: RepositoryHandler) {
    with(repositories) {
        google()
        jcenter()
        mavenCentral()
        Config.repositoryList.forEach {
            maven { url = URI(it) }
        }
    }
}

fun Project.readVersionFile(appType: String, buildType: String): Properties {
    val versionPropsFile: File
    val path = "${System.getProperty("user.dir")}/settings/versions/$appType"
    if (buildType == Config.Flavors.production)
        versionPropsFile = file("$path/${Config.Flavors.production}.properties")
    else if (buildType == Config.Flavors.qa)
        versionPropsFile = file("$path/${Config.Flavors.qa}.properties")
    else if (buildType == Config.Flavors.development)
        versionPropsFile = file("$path/${Config.Flavors.development}.properties")
    else if (buildType == Config.Application.propertyProject)
        versionPropsFile = file("$path/${Config.Application.propertyProject}.properties")
    else
        throw FileNotFoundException ("Unknown app variant type")

    if (versionPropsFile.canRead()) {
        val versionProps = Properties()
        versionProps.load(FileInputStream (versionPropsFile))
        return versionProps
    } else {
        throw GradleException ("Could not read properties! - readVersionFile")
    }
}

fun readVersion(appType: String, buildType: String, project: Project, version: (String, Int) -> Unit) {
    val file = project.readVersionFile(appType, buildType)
    val name = "${file["VERSION_MAJOR"]}.${file["VERSION_MINOR"]}.${file["VERSION_PATCH"]}"
    val code = "${file["VERSION_CODE"]}".toInt()
    version.invoke(name, code)
}

fun readAppSetting(appType: String, project: Project, setting: (String, String) -> Unit) {
    val file = project.readVersionFile(appType, Config.Application.propertyProject)
    val id = "${file["APPLICATION_ID"]}"
    val fabricKey = "${file["FABRIC_API_TOKEN"]}"
    setting.invoke(id, fabricKey)
}

enum class DepensType {
    IMPLEMENTATION, API, COMPILE_ONLY, RUNTIME_ONLY, TEST_IMPLEMENTATION, ANDROID_TEST_IMPLEMENTATION
}

class DepensBuilder {
    private val deps = ArrayList<String>()

    fun deps(content: String): DepensBuilder {
        deps.add(content)
        return this
    }

    fun build(dependencies: DependencyHandler, type: DepensType) {
        val typeName = when(type) {
            DepensType.IMPLEMENTATION -> "implementation"
            DepensType.API -> "api"
            DepensType.COMPILE_ONLY -> "compileOnly"
            DepensType.RUNTIME_ONLY -> "runtimeOnly"
            DepensType.TEST_IMPLEMENTATION -> "testImplementation"
            DepensType.ANDROID_TEST_IMPLEMENTATION -> "androidTestImplementation"
            else -> "implementation"
        }
        deps.forEach {
            dependencies.add(typeName, it)
        }
    }

    operator fun String.unaryPlus() = deps(this)
}

fun DependencyHandlerScope.implementations(func: DepensBuilder.() -> Unit) {
    val depensBuilder: DepensBuilder = DepensBuilder()
    depensBuilder.func()
    depensBuilder.build(dependencies, DepensType.IMPLEMENTATION)
}

fun DependencyHandlerScope.compileOnlys(func: DepensBuilder.() -> Unit) {
    val depensBuilder: DepensBuilder = DepensBuilder()
    depensBuilder.func()
    depensBuilder.build(dependencies, DepensType.COMPILE_ONLY)
}

fun DependencyHandlerScope.apis(func: DepensBuilder.() -> Unit) {
    val depensBuilder: DepensBuilder = DepensBuilder()
    depensBuilder.func()
    depensBuilder.build(dependencies, DepensType.API)
}

fun DependencyHandlerScope.runtimeOnlys(func: DepensBuilder.() -> Unit) {
    val depensBuilder: DepensBuilder = DepensBuilder()
    depensBuilder.func()
    depensBuilder.build(dependencies, DepensType.API)
}

fun DependencyHandlerScope.testImplementations(func: DepensBuilder.() -> Unit) {
    val depensBuilder: DepensBuilder = DepensBuilder()
    depensBuilder.func()
    depensBuilder.build(dependencies, DepensType.TEST_IMPLEMENTATION)
}

fun DependencyHandlerScope.androidTestImplementations(func: DepensBuilder.() -> Unit) {
    val depensBuilder: DepensBuilder = DepensBuilder()
    depensBuilder.func()
    depensBuilder.build(dependencies, DepensType.ANDROID_TEST_IMPLEMENTATION)
}