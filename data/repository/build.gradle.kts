@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.sample.android.library)
}

android {
    namespace = "com.example.data.repository"
}

dependencies {
    implementation(project(":model"))
    implementation(project(":data:api"))
    implementation(project(":data:local"))
    implementation(project(":data:preferences"))
    implementation(libs.kotlinx.coroutines.android)

    // Unit testing
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}
