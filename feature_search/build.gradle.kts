plugins {
    id("com.android.dynamic-feature")
    kotlin("android")
    kotlin("kapt")
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
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
    implementation(project(":library_base"))
    implementation(project(":library_data"))
    implementation(GeneralLibs.KOTLIN_STDLIB)
    implementation(SupportLibs.ANDROIDX_CORE_KTX)
    implementation(SupportLibs.VIEW_PAGER_2)

    // Room - Local Data Storage
    implementation(RoomLibs.RUNTIME)
    kapt(RoomLibs.COMPILER)
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(RoomLibs.KTX)

    // Paging - results paging
    implementation(PagingLibs.PAGING_RUNTIME)

    // Retrofit - Api wrapper
    implementation(RetrofitLibs.retrofit)
    implementation(RetrofitLibs.converterMoshi)

    // Coil - Image Loading and Memory Management
    implementation(CoilLibs.coil)

    addTestDependencies()
}
