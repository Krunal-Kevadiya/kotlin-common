import com.android.build.gradle.internal.dsl.TestOptions
import org.gradle.api.tasks.testing.logging.TestLogging

val qualityConfigDir = "$project.rootDir/settings/codequality"
val sourceDir = "$project.rootDir"

android {
    lintOptions {
        // Turns off checks for the issue IDs you specify.
        disable("NewApi", "RestrictedApi", "TypographyFractions", "TypographyQuotes")
        // Turns on checks for the issue IDs you specify. These checks are in
        // addition to the default lint checks.
        enable("RtlHardcoded", "RtlCompat", "RtlEnabled")
        // To enable checks for only a subset of issue IDs and ignore all others,
        // list the issue IDs with the 'check' property instead. This property overrides
        // any issue IDs you enable or disable using the properties above.
        check("InlinedApi", "HandlerLeak")
        // If set to true, turns off analysis progress reporting by lint.
        isQuiet = true

        xmlReport = true
        htmlReport = true
        isCheckAllWarnings = true
        isWarningsAsErrors = true
        isAbortOnError = true
        setLintConfig(file("$qualityConfigDir/lint/javalintrc.xml"))
    }

    tasks.withType<Test> {
        testLogging {
            events("passed", "skipped", "failed", "standardOut", "standardError")
            //outputs.upToDateWhen { false }
            showStandardStreams = true
        }
        setJvmArgs(mutableListOf("-noverify"))
    }
}