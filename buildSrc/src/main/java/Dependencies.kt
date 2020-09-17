object Sdk {
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 29
    const val COMPILE_SDK_VERSION = 29
}

object Versions {
    const val ANDROIDX_TEST_EXT = "1.1.1"
    const val MATERIAL = "1.3.0-alpha02"
    const val ANDROIDX_TEST = "1.2.0"
    const val APPCOMPAT = "1.1.0"
    const val CONSTRAINT_LAYOUT = "1.1.3"
    const val CORE_KTX = "1.2.0"
    const val ESPRESSO_CORE = "3.2.0"
    const val JUNIT = "4.13"
    const val KTLINT = "0.37.2"
    const val NAV = "2.3.0"
    const val KOTLIN = "1.4.0"
    const val KOIN = "2.0.1"
    const val RETROFIT = "2.8.2"
    const val COROUTINES = "1.3.9"
    const val COROUTINES_PLAY_SERVICES = "1.1.1"
    const val LIFECYCLE = "2.2.0"
    const val LIFECYCLE_LIVEDATA = "2.3.0-alpha07"
    const val GLIDE = "4.11.0"
}

object BuildPluginsVersion {
    const val AGP = "4.0.0"
    const val DETEKT = "1.10.0"
    const val KOTLIN = "1.3.72"
    const val KTLINT = "9.2.1"
    const val VERSIONS_PLUGIN = "0.28.0"
}

object GeneralLibs {
    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
}

object SupportLibs {
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val ANDROIDX_CONSTRAINT_LAYOUT =
        "com.android.support.constraint:constraint-layout:${Versions.CONSTRAINT_LAYOUT}"
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
}

object CoroutineLibs {
    const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
    const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
    const val PLAY_SERVICES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.COROUTINES_PLAY_SERVICES}"
    const val LIFECYCLE_VIEWMODEL =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
    const val LIFECYCLE_LIVEDATA =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_LIVEDATA}"
}

object KoinLibs {
    const val Android = "org.koin:koin-android:${Versions.KOIN}"
    const val ViewModels = "org.koin:koin-androidx-viewmodel:${Versions.KOIN}"
}

object NavigationLibs {
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAV}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAV}"
    const val NAVIGATION_DYNAMIC_FEATURES =
        "androidx.navigation:navigation-dynamic-features-fragment:${Versions.NAV}"
}

object RetrofitLibs {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT}"
}

object GlideLibs {
    const val glide = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val compiler = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"
}

object TestingLib {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val KOIN = "org.koin:koin-test:${Versions.KOIN}"
}

object AndroidTestingLib {
    const val ANDROIDX_TEST_RULES = "androidx.test:rules:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_RUNNER = "androidx.test:runner:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST_EXT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}
