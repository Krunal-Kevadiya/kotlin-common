[![](https://jitpack.io/v/Krunal-Kevadiya/kotlin-common.svg)](https://jitpack.io/#Krunal-Kevadiya/kotlin-common)
# Application Documentation
1. **`Fastlane Installation`**
    Make sure you have the latest version of the **_Xcode_** command line tools installed:
    ```sh
        xcode-select --install
    ```
    Install **_fastlane_** using
    ```sh
        [sudo] gem install fastlane -NV
    ```
    or alternatively using
    ```sh
        brew cask install fastlane
    ```
2. **`Build generate & Upload in fabric`**
    1. Configuration
        Edit your fastlane file (Project directory -> fastlane -> Fastfile)
        * Set your application module
            APP_TYPE = "app"
            MODULES = "App"
        * Set you fabric api key
            FABRIC_API_TOKEN = "b7353e549e0f0d809cf77962a39ebbb551cc185f"
        * Set you fabric secret key
            FABRIC_BUILD_SECRET = "a16bf773020f43aeb3f24bbe2707516d9c5a1a3089d82fb25790cd52d472dd11"
        * Added your testing email when you have invite to test your application to internal
            TESTER_EMAIL = \['krunal@linkerlogic.com', 'r.d.thakrar@gmail.com', 'rcbrcb13@gmail.com', 'terrydan710@gmail.com']
        * Added client testing email when you have invite to test your application to client
            CLIENT_EMAIL = \['krunal@linkerlogic.com', 'r.d.thakrar@gmail.com', 'rcbrcb13@gmail.com', 'terrydan710@gmail.com']
    2. Fastlane command to execute in terminal or CMD
        There are single type of argument pass like below
        `versionChange : major, minor, patch, reset`
        Here reset value is used for set default value of version code and name.
        your project version code & name display like
        - Version Code
            format like "Version code - ${VERSION_CODE}"
            Example :- versionCode = 1
        - Version Name
            format like "Version Name - %d.%d.%d(%d)" "${VERSION_MAJOR}" "${VERSION_MINOR}" "${VERSION_PATCH}" "${VERSION_CODE}"
            Example :- versionName = 1.0.0(1)
        1. For release in Developer
            ```sh
                fastlane devVariant versionChange:"patch"
            ```
        2. For release in QA
            ```sh
                fastlane qaVariant versionChange:"patch"
            ```
        3. For release in Production
            ```sh
                fastlane productionVariant versionChange:"patch"
            ```
3. **`Static Code Analysis Tool`**
    Here eight different type of Static Code Analysis tool integrated like below
    1.  CheckStyle
    2.  Lint
    3.  Findbugs
    4.  PMD
    5.  KtLint
    6.  Detekt
    7.  Spotless
    8.  Sonarqube

    With execute side by side and generate report file when you have resolve tool error
    Format :- like TXT, XML and JSON in your local settings folder.
    Path :- App root dir -> settings -> reports -> tool type).
    1. Configuration
        Also you have use those tool using single file import/apply in your build.gradle file.
        1. App level build.gradle file
            ```groovy
                apply from: '../settings/codequality/quality.gradle'
            ```
        2. Project level build.gradle file
            ```groovy
                buildscript {
                    apply from: './settings/dependencies.gradle'
                    addRepos(repositories)

                    dependencies {
                        classpath deps.staticTools.sonarqube
                        classpath deps.staticTools.jacoco
                    }
                }
                plugins {
                    id("com.diffplug.gradle.spotless").version("3.15.0")
                }
                allprojects {
                    addRepos(repositories)
                    addProjectSetting()
                }
                apply plugin: 'org.sonarqube'
            ```
    2. How to run static code analysis tool
        Run all tool excluding **Sonarqube** using command like
        ```sh
            ./gradlew check
        ```
        Now you have run separately then use
        1. For JAVA File
            ```sh
                ./gradlew checkstyle
                ./gradlew lint
                ./gradlew findbugs
                ./gradlew pmd
            ```
        2. For KOTLIN File
            ```sh
                ./gradlew ktlint
                ./gradlew ktlintFormat
                ./gradlew detektcheck
                ./gradlew spotlessApply
                ./gradlew spotlessCheck
            ```
        3. Sonarqube
            ```sh
                ./gradlew sonarqube -Dsonar.host.url=http://localhost:9000
                ./gradlew sonarqube -Dsonar.host.url=http://172.16.1.109:9000
                ./gradlew sonarqube -Dsonar.host.url=http://ci.simformsolutions.com:9000
            ```
        Also you have link preBuild and assemble task like you build.gradle file
        ```groovy
            preBuild.dependsOn('checkstyle')
            assemble.dependsOn('lint')
            check.dependsOn 'checkstyle', 'detekt', 'findbugs', 'lint', 'ktlint', 'ktlintFormat', 'pmd', 'spotlessApply', 'spotlessCheck'
        ```
4. **`Auto increment version code & name`**
    That part for used when you have run/execute fastlane command and update you version without forgot
    1. Configuration
        1. App level build.gradle file
            ```groovy
                android {
                    defaultConfig {
                        applicationId readApplicationId()
                        manifestPlaceholders = [crashlyticsKey: readCrashlyticsKey()]
                    }

                    flavorDimensions "mode"
                    productFlavors {
                        production {
                            dimension "mode"
                            applicationId readApplicationId()
                            versionCode readVersionCode(flavorFiles.production)
                            versionName readVersionName(flavorFiles.production)
                        }

                        qa {
                            dimension "mode"
                            applicationId readApplicationId()
                            versionCode readVersionCode(flavorFiles.qa)
                            versionName readVersionName(flavorFiles.qa)
                        }

                        development {
                            dimension "mode"
                            applicationId readApplicationId()
                            versionCode readVersionCode(flavorFiles.development)
                            versionName readVersionName(flavorFiles.development)
                        }
                    }
                }

                def readVersionName(def buildType = "buildVariant") {
                    def version = readVersionFile("app", buildType)
                    return "${version['VERSION_MAJOR']}.${version['VERSION_MINOR']}.${version['VERSION_PATCH']}(${version['VERSION_CODE']})"
                }

                def readVersionCode(def buildType = "buildVariant") {
                    def version = readVersionFile("app", buildType)
                    def build = version['VERSION_CODE'] as int
                    return build
                }

                def readCrashlyticsKey() {
                    def version = readVersionFile("app", flavorFiles.project)
                    return "${version['FABRIC_API_TOKEN']}"
                }

                def readApplicationId() {
                    def version = readVersionFile("app", flavorFiles.project)
                    return "${version['APPLICATION_ID']}"
                }
                /****************************
                 * Here "app" is app module *
                 ****************************/
            ```
        2. AndroidManifest.xml file
            ```xml
                 <application>
                        <!-- Fabric -->
                        <meta-data
                            android:name="io.fabric.ApiKey"
                            android:value="${crashlyticsKey}" />
                 </application>
            ```
5. **`Release SDK in maven`**
    I figured it would have been easier to publish to JCenter than it actually was! I also struggled to
    find a sequence of instructions that actually worked, so these are the steps that worked for me
    1. Release Gradle file
        Firstly, open your build.gradle file for your module. We’re going to include a script to generate
        our .aar/.jar files (Android Library files) from this Setting/maven dir.
    2. Module Gradle
        The code we’ll need to insert into our gradle file, right at the bottom, is the following:
        ```groovy
            apply from: '../settings/maven/android-release-aar.gradle'
        ```
        OR
        ```groovy
            apply from: '../settings/maven/android-release-jar.gradle'
        ```
    3. Module Gradle
        Next we need to add a section into the same gradle file, below the first line that says the project
        is an android library.
        This will be the information people will use as a gradle dependency in their own projects! So think
        carefully about what group and artifact id’s will be!
        My information looks like the following:
        ```groovy
            ext {
                PUBLISH_GROUP_ID = 'com.kevadiyakrunalk'
                PUBLISH_ARTIFACT_ID = 'kotlin-common'
                PUBLISH_VERSION = '1.0.0'
            }
        ```
    4. Make file
        Next, we’re ready to compile our library. You’ll need to open up a terminal (in Mac—you’ll have to
        Google how to do this bit in Windows) and navigate to the root of your library project and then type
        the following code (note that this might take a while and generate a lot of lines in your terminal)
        ```bash
            ./gradlew clean build generateRelease
        ```
        When it succeeds you should see the following “BUILD SUCCESSFUL”—it also helpfully tells you the
        full location of the release zip folder that we’ll need later!
        Path -> Project name dir -> Module name dir -> build -> release -> zip here
    5. Bintray
        Bintray seemed to mentioned a LOT when I was Googling around how to upload my library. It’s a site
        that allows you to host files for other people to download.
        1. Create New Repository
            The first thing to do is to create a new repository for your library. This works out as an organisation name,
            you can have many projects inside. Which is a little different from GitHub.
            Additionally, you can give it any name that you’d like, as long as it makes it easy for you to remember
            what it is! People installing your library won’t ever see or use this bit!
        2. New Package
            Next, we need to create a package inside. This works out like the project name. Again nobody else
            other than you (and any other co-creators) will see this name.
            I named my project after the GitHub Org I had set up for my Android open source libraries, and the
            package was the current project I wanted to upload. If that helps!
        3. New Version
            Finally, we get to create a new version. Versions don’t have to be traditional numbers, you could
            give them any name you want. Again, this information won’t be used by anybody else. Which is fairly
            confusing the first time you go through all of these steps!
        4. Upload files
            So now we have our version, we can upload our files. This button took me a LONG time to find—but
            at the top of the page after you have created a version there should be an “upload files” button.
            I’ve highlighted the button in red below:
        5. Tiny Check Box Bit
            Here, you should be able to click to add your zip folder (the one we made in the terminal earlier)
            and upload it.
            Before saving your changes, make sure you tick the tiny check box about exploding the archive.
            Without ticking this your project just won’t work!!!
        6. JCenter request
            You should receive some confirmation when your library is uploaded. The next bit is to ask JCenter
            to “link” with your project, which makes your library discoverable with gradle dependencies.
            If you go to your “project” page in bintray—there will be a little button above “linked to” to
            add your project to JCenter. Here you’ll have to fill out a message and wait to be accepted. Though
            that has never taken me more than a few hours to get accepted
    6. What to tell other people!
        This is the bit that had me stumped for the longest of time—and took me a good few days once my
        project was uploaded to actually download it.
        You have to wait until you get a notification in bintray that your library has been accepted to join
        JCenter before anyone can begin to use your library. But what information do you give them? the
        project and package information from bintray? This took me several days to work out, and is only
        really obvious upon reflection 😥
        Whatever you put into your build.gradle file inside Android Studio is the bit that actually matters
        — the publish group : publish artefact : and version make up the gradle dependency.
        For example my dependency looks like this:
        ```groovy
            implementation 'com.kevadiyakrunalk:kotlin-common:1.0.0'
        ```
----
This README.md is auto-generated and will be re-generated every time [fastlane](https://fastlane.tools) is run.
More information about fastlane can be found on [fastlane.tools](https://fastlane.tools).
The documentation of fastlane can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
