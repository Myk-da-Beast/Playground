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
    dynamicFeatures = mutableSetOf()
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))

    implementation(project(":library-android"))
    implementation(project(":library-kotlin"))
    implementation(project(":feature_search"))
    implementation(project(":feature_teams"))

    // implementation(SupportLibs.MATERIAL)
    api("com.google.android.material:material:1.3.0-alpha02")

    implementation(SupportLibs.ANDROIDX_APPCOMPAT)
    implementation(SupportLibs.ANDROIDX_CONSTRAINT_LAYOUT)
    implementation(SupportLibs.ANDROIDX_CORE_KTX)

    api(NavigationLibs.NAVIGATION_FRAGMENT)
    api(NavigationLibs.NAVIGATION_UI)
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.1")
    implementation("androidx.navigation:navigation-fragment:2.3.0")
    implementation("androidx.navigation:navigation-ui:2.3.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.0")
    // api(NavigationLibs.NAVIGATION_DYNAMIC_FEATURES)

    addTestDependencies()
}
