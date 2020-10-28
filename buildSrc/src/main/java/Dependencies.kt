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
    const val TIMBER = "4.7.1"
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
    const val COIL = "0.13.0"
    const val ROOM = "2.3.0-alpha02"
    const val VIEW_PAGER_2 = "1.0.0"
    const val PAGING = "3.0.0-alpha07"
}

object BuildPluginsVersion {
    const val AGP = "4.2.0-alpha13"
    const val DETEKT = "1.10.0"
    const val KOTLIN = "1.3.72"
    const val KTLINT = "9.2.1"
    const val VERSIONS_PLUGIN = "0.28.0"
}

object GradlePlugins {
    const val SAFE_ARGS = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAV}"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
}

object GeneralLibs {
    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
}

object SupportLibs {
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val ANDROIDX_CONSTRAINT_LAYOUT =
        "com.android.support.constraint:constraint-layout:${Versions.CONSTRAINT_LAYOUT}"
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val VIEW_PAGER_2 = "androidx.viewpager2:viewpager2:${Versions.VIEW_PAGER_2}"
}

object TimberLibs {
    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"
}

object CoroutineLibs {
    const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
    const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
    const val PLAY_SERVICES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.COROUTINES_PLAY_SERVICES}"
    const val LIFECYCLE_VIEWMODEL =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
    const val LIFECYCLE_COMMON = "androidx.lifecycle:lifecycle-common-java8:${Versions.LIFECYCLE}"
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

object PagingLibs {
    const val PAGING_RUNTIME = "androidx.paging:paging-runtime-ktx:${Versions.PAGING}"
}

object RetrofitLibs {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT}"
}

object CoilLibs {
    const val coil = "io.coil-kt:coil:${Versions.COIL}"
}

object RoomLibs {
    const val RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
    const val COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
    const val KTX = "androidx.room:room-ktx:${Versions.ROOM}"
}

private object TestingLibraryVersions {
    const val JUNIT = "4.13"
    const val KLUENT = "1.61"
    const val ANDROIDX_TEST_EXT = "1.1.1"
    const val ANDROIDX_TEST = "1.2.0"
    const val ESPRESSO_CORE = "3.2.0"
    const val MOCKK = "1.10.0"
    const val ARCH = "2.1.0"
    const val KOIN = "2.0.1"
    const val ROOM = "2.3.0-alpha02"
    const val COROUTINES = "1.3.9"
    const val PAGING = "3.0.0-alpha07"
}

object TestingLibs {
    const val JUNIT = "junit:junit:${TestingLibraryVersions.JUNIT}"
    const val KLUENT = "org.amshove.kluent:kluent:${TestingLibraryVersions.KLUENT}"
    const val KLUENT_ANDROID = "org.amshove.kluent:kluent-android:${TestingLibraryVersions.KLUENT}"
    const val ANDROIDX_TEST_RUNNER = "androidx.test:runner:${TestingLibraryVersions.ANDROIDX_TEST}"
    const val ESPRESSO_CORE =
        "androidx.test.espresso:espresso-core:${TestingLibraryVersions.ESPRESSO_CORE}"
    const val ANDROIDX_TEST_RULES = "androidx.test:rules:${TestingLibraryVersions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_EXT_JUNIT =
        "androidx.test.ext:junit:${TestingLibraryVersions.ANDROIDX_TEST_EXT}"
    const val MOCKK = "io.mockk:mockk:${TestingLibraryVersions.MOCKK}"
    const val COROUTINES_TEST =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${TestingLibraryVersions.COROUTINES}"
    const val ANDROID_X_TEST = "androidx.arch.core:core-testing:${TestingLibraryVersions.ARCH}"
    const val KOIN = "org.koin:koin-test:${TestingLibraryVersions.KOIN}"
    const val ROOM = "androidx.room:room-testing:${TestingLibraryVersions.ROOM}"
    const val PAGING = "androidx.paging:paging-common:${TestingLibraryVersions.PAGING}"
}
