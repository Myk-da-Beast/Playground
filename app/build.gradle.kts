plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    id("kotlin-android")
}

android {
    compileSdkVersion(Sdk.COMPILE_SDK_VERSION)

    buildFeatures {
        viewBinding = true
    }

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
    dynamicFeatures = mutableSetOf(":feature_search", ":feature_teams")
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))

    implementation(project(":library-android"))
    implementation(project(":library-kotlin"))

    // AndroidX - Hardware support and Compatibility
    api(SupportLibs.MATERIAL)
    api(SupportLibs.ANDROIDX_APPCOMPAT)
    api(SupportLibs.ANDROIDX_CONSTRAINT_LAYOUT)
    api(SupportLibs.ANDROIDX_CORE_KTX)

    // Coroutines - Thread Management
    api(CoroutineLibs.CORE)
    api(CoroutineLibs.ANDROID)
    api(CoroutineLibs.PLAY_SERVICES)
    api(CoroutineLibs.LIFECYCLE_VIEWMODEL)
    api(CoroutineLibs.LIFECYCLE_LIVEDATA)
    api(CoroutineLibs.LIFECYCLE_RUNTIME)

    // Navigation
    api(NavigationLibs.NAVIGATION_FRAGMENT)
    api(NavigationLibs.NAVIGATION_UI)
    api(NavigationLibs.NAVIGATION_DYNAMIC_FEATURES)

    // Koin - Dependency Injection
    api(KoinLibs.Android)
    api(KoinLibs.ViewModels)

    // Retrofit - Api Wrapper
    implementation(RetrofitLibs.retrofit)
    implementation(RetrofitLibs.converterMoshi)

    addTestDependencies()
}
