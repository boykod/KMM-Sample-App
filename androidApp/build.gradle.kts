import com.android.build.gradle.api.ApkVariantOutput

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    with(Config) {
        compileSdkVersion(compileSdk)
        buildToolsVersion(buildToolsVersion)

        defaultConfig {
            applicationId = appPackageName
            minSdkVersion(minSdkVer)
            targetSdkVersion(targetSdkVer)
            versionCode = appVersionCode
            versionName = appVersionName

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            getByName(RELEASE_BUILD_TYPE_NAME) {
                isMinifyEnabled = true
                applicationIdSuffix = appReleaseSuffix
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
            getByName(DEBUG_BUILD_TYPE_NAME) {
                isMinifyEnabled = false
                applicationIdSuffix = appDebugSuffix
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    applicationVariants.all {
        packageApplicationProvider?.let {
            buildOutputs.all {
                with(this as ApkVariantOutput) {
                    outputFileName = "${versionName}-${this.name}.apk"
                }
            }
        }
    }

    dependenciesInfo {
        includeInBundle = false
        includeInApk = false
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + listOf("-Xallow-jvm-ir-dependencies")
    }
}

dependencies {
    implementation(project(":shared"))

    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.0")
    implementation("androidx.appcompat:appcompat:1.3.0-beta01")


    /*Compose*/
    implementation("androidx.compose.ui:ui:${Versions.compose}")
    implementation("androidx.compose.runtime:runtime-livedata:${Versions.compose}")
    implementation("androidx.compose.runtime:runtime:${Versions.compose}")
    implementation("androidx.compose.foundation:foundation:${Versions.compose}")
    implementation("androidx.compose.foundation:foundation-layout:${Versions.compose}")
    implementation("androidx.activity:activity-compose:${Versions.compose_activity}")
    implementation("androidx.compose.material:material:${Versions.compose}")
    implementation("androidx.compose.ui:ui-tooling:${Versions.compose}")
    implementation("dev.chrisbanes.accompanist:accompanist-coil:0.6.2")
}