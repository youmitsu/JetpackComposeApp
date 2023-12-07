@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.sample.android.library)
}

android {
    namespace = "com.example.data.preferences"
}

dependencies {
    implementation(project(":model"))
    implementation(libs.kotlinx.coroutines.android)

    // Unit testing
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}
