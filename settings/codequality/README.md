Korlin Common Application Documentation
=================================
    
fastlane
========
# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```
xcode-select --install
```

Install _fastlane_ using
```
[sudo] gem install fastlane -NV
```
or alternatively using `brew cask install fastlane`

# Available Actions
## Android
```
There are two type of argument pass like below
versionChange : major, minor, patch, reset
```
### android devVariant
```
fastlane devVariant versionChange:"patch"
```
Release for development
### android qaVariant
```
fastlane qaVariant versionChange:"patch"
```
Release for qa
### android productionVariant
```
fastlane productionVariant versionChange:"patch"
```
Release for production

Static Code Analysis Tool
=========================
Here seven type of Static Code Analysis tool integrated like below
```
1. CheckStyle
2. Lint
3. Findbugs
4. PMD
5. KtLint
6. Detekt
7. Spotless
8. Sonarqube
```
With execute side by side and generate report file when you have resolve tool error
(Format like TXT, XML and JSON in your local settings folder).

Also you have use those tool using single file import/apply in your build.gradle file.
###App level build.gradle file in
``` 
apply from: '../settings/codequality/quality.gradle'
dependencies {
    classpath "com.dicedmelon.gradle:jacoco-android:0.1.2"
    classpath "org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:2.6.2"
}
apply plugin: 'org.sonarqube'
```
###Project level build.gradle file in
``` 
plugins {
    id "com.diffplug.gradle.spotless" version "3.15.0"
}
```
###How to run static code analysis tool
Run all tool using single command like 
```
./gradlew check
```
Now you have run separately then use
- For JAVA File
```
./gradlew checkstyle
./gradlew lint
./gradlew findbugs
./gradlew pmd
```
- For KOTLIN File
```
./gradlew ktlint
./gradlew ktlintFormat
./gradlew spotlessApply
./gradlew spotlessCheck
./gradlew detektcheck
```
- Sonarqube
Install sonarqube in mac follow this step 
- http://mobiosolutions.com/install-sonarqube-installation-guide-mac-os/
```
./gradlew sonarqube -Dsonar.host.url=http://localhost:9000
./gradlew sonarqube -Dsonar.host.url=http://ci.simformsolutions.com:9000
```
Also you have configure using preBuild and assemble command like you build.gradle file
```
preBuild.dependsOn('checkstyle')
assemble.dependsOn('lint')
check.dependsOn 'checkstyle', 'detekt', 'findbugs', 'lint', 'ktlint', 'ktlintFormat', 'pmd',
    'spotlessApply', 'spotlessCheck'
```

----

This README.md is auto-generated and will be re-generated every time [fastlane](https://fastlane.tools) is run.
More information about fastlane can be found on [fastlane.tools](https://fastlane.tools).
The documentation of fastlane can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
