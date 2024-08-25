import com.diffplug.gradle.spotless.SpotlessExtension

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.dagger.hilt) apply false
    alias(libs.plugins.spotless)
}

subprojects {
    plugins.apply(rootProject.libs.plugins.spotless.get().pluginId)
    configure<SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            ktlint()
            indentWithSpaces()
            endWithNewline()
        }
        kotlinGradle {
            target("**/*.gradle.kts")
            ktlint()
            indentWithSpaces()
            endWithNewline()
        }
    }
}