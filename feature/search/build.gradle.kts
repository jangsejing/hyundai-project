plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.jess.hyundai.feature.search"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    buildTypes {
        release {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {

    // project
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":model"))
    implementation(project(":network"))
    implementation(project(":navigator"))
    implementation(project(":ui"))

    // androidx
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.androidx.lifecycle.runtimes)

    // compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.androidx.composes)

    // coil
    implementation(libs.coil.compose)

    // dagger
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)

    // retrofit
    implementation(libs.bundles.retrofits)

    // other
    implementation(libs.timber.core)
}