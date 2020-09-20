plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(Sdk.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(Sdk.MIN_SDK_VERSION)
        targetSdkVersion(Sdk.TARGET_SDK_VERSION)
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

    packagingOptions {
        exclude("META-INF/atomicfu.kotlin_module")
    }
}

dependencies {
    implementation(GeneralLibs.KOTLIN_STDLIB)
    implementation(SupportLibs.ANDROIDX_CORE_KTX)

    // Room - Local Data Storage
    implementation(RoomLibs.RUNTIME)
    kapt(RoomLibs.COMPILER)
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(RoomLibs.KTX)
    // optional - Test helpers
    testImplementation(TestingLib.ROOM)

    implementation(KoinLibs.Android)

    addTestDependencies()
}
