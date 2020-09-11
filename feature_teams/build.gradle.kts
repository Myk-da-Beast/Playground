plugins {
    id("com.android.dynamic-feature")
    kotlin("android")
}

android {
    compileSdkVersion(Sdk.COMPILE_SDK_VERSION)
    // buildToolsVersion "29.0.3"

    defaultConfig {
        minSdkVersion(Sdk.MIN_SDK_VERSION)
        targetSdkVersion(Sdk.TARGET_SDK_VERSION)

        applicationId = AppCoordinates.APP_ID
        versionCode = AppCoordinates.APP_VERSION_CODE
        versionName = AppCoordinates.APP_VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = true
    }
    buildFeatures.viewBinding = true
}

dependencies {
    implementation(project(":app"))
    implementation(GeneralLibs.KOTLIN_STDLIB)
    implementation(SupportLibs.ANDROIDX_CORE_KTX)

    addTestDependencies()
}
