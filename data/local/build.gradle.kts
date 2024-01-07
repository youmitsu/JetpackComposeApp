@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.sample.android.library)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.data.local"
}

dependencies {
    implementation(project(":model"))
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.room)
    ksp(libs.room.compiler)

    // Unit testing
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}
