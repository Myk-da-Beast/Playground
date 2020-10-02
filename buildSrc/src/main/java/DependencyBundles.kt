import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addTestDependencies() {
    testImplementation(project(":library_testUtils"))

    testImplementation(TestingLibs.JUNIT)
    testImplementation(TestingLibs.KLUENT)
    testImplementation(TestingLibs.MOCKK)
    testImplementation(TestingLibs.COROUTINES_TEST)
    testImplementation(TestingLibs.ANDROID_X_TEST)
    testImplementation(TestingLibs.KOIN)
    testImplementation(TestingLibs.ROOM)

    androidTestImplementation(TestingLibs.ANDROIDX_TEST_RUNNER)
    androidTestImplementation(TestingLibs.ESPRESSO_CORE)
    androidTestImplementation(TestingLibs.KLUENT_ANDROID)
    androidTestImplementation(TestingLibs.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(TestingLibs.ANDROIDX_TEST_RULES)
}

/*
 * These extensions mimic the extensions that are generated on the fly by Gradle.
 * They are used here to provide above dependency syntax that mimics Gradle Kotlin DSL
 * syntax in module\build.gradle.kts files.
 */
private fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

private fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

private fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

private fun DependencyHandler.project(
    path: String,
    configuration: String? = null
): ProjectDependency {
    val notation = if (configuration != null) {
        mapOf("path" to path, "configuration" to configuration)
    } else {
        mapOf("path" to path)
    }

    return uncheckedCast(project(notation))
}

@Suppress("unchecked_cast", "nothing_to_inline", "detekt.UnsafeCast")
private inline fun <T> uncheckedCast(obj: Any?): T = obj as T
